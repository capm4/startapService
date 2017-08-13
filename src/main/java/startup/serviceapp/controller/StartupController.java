package startup.serviceapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import startup.serviceapp.model.Startup;
import startup.serviceapp.service.CategoryService;
import startup.serviceapp.service.StartupService;
import startup.serviceapp.service.UserService;
import startup.serviceapp.validator.StartupValidator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Controller for {@link startup.serviceapp.model.Startup}'s pages.
 *
 * @author Alexander Kruglov
 * @version 1.0
 */

@Controller
public class StartupController extends HttpServlet {
	@Autowired
	private StartupService startupService;

	@Autowired
	private UserService userService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private StartupValidator startupValidator;

	@RequestMapping(value="addstartup", method = RequestMethod.GET)
	public String addStartup(Model model){
		model.addAttribute("startupForm", new Startup());
		return "addstartup";
	}

	@RequestMapping(value="addstartup", method = RequestMethod.POST)
	public String addStartup(@ModelAttribute("starrupForm") Startup startupForm,
	                         @RequestParam("categoryId") long categoryId, BindingResult bindingResult, HttpServletRequest request){
		startupValidator.validate(startupForm, bindingResult);
		if (!userService.isAuthenticated(request)) {
			return "redirect:/login";
		}
		if (bindingResult.hasErrors()){
			return "addstartup";
		}
		startupForm.setCategory(categoryService.getCategoryById(categoryId));
		startupForm.getUsers().add(userService.getAuthenticatedUser(request));
		startupService.save(startupForm);
		return "redirect:allstartups";
	}

	@RequestMapping(value = "allstartups", method=RequestMethod.GET)
	public String allstartups(Model model){
		model.addAttribute("startup", new Startup());
		model.addAttribute("allStartups", this.startupService.getAllApproveStartups());
		model.addAttribute("mobileStartups", this.startupService.getAllMobileStartups());
		model.addAttribute("investmentStartups",this.startupService.getAllInvestmentStartups());
		model.addAttribute("businessStertups",this.startupService.getAllBusinessStartups());
		model.addAttribute("categories",this.categoryService.getAllCategories());
		return "allstartups";
	}

	@RequestMapping(value = "mystartups", method = RequestMethod.GET)
	public String mystartups(Model model, HttpServletRequest request){
		model.addAttribute("startup", new Startup());
		model.addAttribute("user", this.userService.getAuthenticatedUser(request));
		model.addAttribute("allUserStartups", this.startupService.getStartupsByUser(userService.getAuthenticatedUser(request)));
		return "mystartups";
	}

	@RequestMapping(value = "/error")
	public  String errorPage(){
		return "error";
	}
}
