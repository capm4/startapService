package startup.serviceapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import startup.serviceapp.repository.RoleRepository;
import startup.serviceapp.service.SecurityService;
import startup.serviceapp.service.StartupService;
import startup.serviceapp.service.UserService;
import startup.serviceapp.validator.UserValidator;

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

	@RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
	public String index(Model model){
		return "index";
	}
}
