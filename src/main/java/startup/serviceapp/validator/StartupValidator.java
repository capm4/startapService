package startup.serviceapp.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import startup.serviceapp.model.Startup;
import startup.serviceapp.service.StartupService;

/**
 * Validator for {@link startup.serviceapp.model.Startup}
 *
 * @author  Alexander Kruglov
 * @version 1.0
 */

@Component
public class StartupValidator implements Validator {

	@Autowired
	StartupService startupService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Startup.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Startup startup = (Startup) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "needed_investment", "Required");

		if (startup.getName().length()< 5 || startup.getName().length() > 32){
			errors.rejectValue("name", "Size.startupForm.name");
		}
		if(startup.getDescription().length()<10 || startup.getDescription().length()>2500){
			errors.rejectValue("description", "Size.startupForm.description");
		}
		if(startup.getNeeded_investment() < 0){
			errors.rejectValue("neededInvestment", "Size.startupForm.neededInvestment");
		}
		if(startupService.findByName(startup.getName()) != null){
			errors.rejectValue("name", "Duplicate.startupForm.name");
		}
	}
}
