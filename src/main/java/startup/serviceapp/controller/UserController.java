package startup.serviceapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import startup.serviceapp.model.UserDB;
import startup.serviceapp.repository.RoleRepository;
import startup.serviceapp.service.SecurityService;
import startup.serviceapp.service.StartupService;
import startup.serviceapp.service.UserService;
import startup.serviceapp.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller for {@link startup.serviceapp.model.UserDB}' pages
 *
 *@author Alexander Kruglov
 *@version 1.0
 */

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private StartupService startupService;


	@RequestMapping(value = "/registration", method =RequestMethod.GET )
	public String registration(Model model){
		model.addAttribute("userForm", new UserDB());
		return "registration";
	}

	@RequestMapping(value = "registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") UserDB userForm,
	                           @RequestParam("roleId") long id, BindingResult bindingResult,
	                           Model model){
		userValidator.validate(userForm, bindingResult);
		if(bindingResult.hasErrors()){
			return "registration";
		}
		userForm.getRoles().add(roleRepository.getOne(id));
		userService.save(userForm);
		securityService.autoLogin(userForm.getLogin(), userForm.getConfirmPassword());
		return "redirect:/allstartups";
	}

	@RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
	public String index(Model model){
		return "index";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(Model model, HttpServletRequest request){
		if(userService.isAdmin(request)){
			model.addAttribute("allstatups", startupService.getAllStartups());
			return "admin";
		}
		else{
			return "redirect:allstartups";
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(value = "error", required = false)String error, String logout, Model model){
		if(error != null){
			model.addAttribute("error", "Login or password is incorrect.");
		}
		if(logout != null){
			model.addAttribute("message","Logged out successfully.");
		}
		return "login";
	}


	@RequestMapping(value = "userdetails", method = RequestMethod.GET)
	public String userDetails(Model model){
		model.addAttribute("userForm", userService.findByLogin(securityService.getLoggedUserLogin()));
		return "userdetails";
	}

	@RequestMapping(value = "userdetails", method = RequestMethod.POST)
	public String userDetails(@ModelAttribute("userForm") UserDB userForm, Model model, HttpServletRequest request){
		userForm.setRoles(userService.findByLogin(userForm.getLogin()).getRoles());
		userForm.setPassword(userService.findByLogin(userForm.getLogin()).getPassword());
		userForm.setStartups(userService.getAuthenticatedUser(request).getStartups());
		userService.saveWithoutEncode(userForm);
		return "userdetails";
	}

	@RequestMapping(value = {"/welcome"}, method = RequestMethod.GET)
	public String welcome(Model model){
		return "welcome";
	}


}
