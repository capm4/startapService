package startup.serviceapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import startup.serviceapp.model.Category;
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

	@RequestMapping("/remove/{id}")
	public String removeStartup(@PathVariable("id")Long id){
		this.startupService.deleteById(id);
		return "redirect:allstartups";
	}

	@RequestMapping(value = "/startupedit/{id}", method = RequestMethod.GET)
	public String editStartup(@PathVariable("id") Long id, Model model, HttpServletRequest request){
		if(this.userService.isStartupOwner(id, request)){
			model.addAttribute("startup", this.startupService.getStartupById(id));
			model.addAttribute("is_authenticated", this.userService.isAuthenticated(request));
			model.addAttribute("categories", this.categoryService.getAllCategories());
			model.addAttribute("category_names", this.categoryService.getAllCategoryNames());
			return "startupedit";
		}
		else{
			return "redirect:/startupdetails/{id}";
		}
	}

	@RequestMapping(value = "startupedit/{id}", method = RequestMethod.POST)
	public String editStartup(@ModelAttribute("startup") Startup startup) {
		Category category = categoryService.getCategoryByName(startup.getCategory().getName());
		startup.setCategory(category);
		Startup startupDB = startupService.getStartupById(startup.getId());
		startup.setUsers(startupDB.getUsers());
		this.startupService.edit(startup);
		return "redirect:/startupdetails/{id}";
	}

	@RequestMapping(value = "/startupdetails/{id}")
	public String startupDetails(@PathVariable("id") long id, Model model, HttpServletRequest request){
		model.addAttribute("startup", this.startupService.getStartupById(id));
		model.addAttribute("is_admin", this.userService.isAdmin(request));
		model.addAttribute("is_owner", this.userService.isStartupOwner(id,request));
		model.addAttribute("is_authenticated", this.userService.isAuthenticated(request));
		model.addAttribute("average_rating", this.startupService.averageRating(id));
		model.addAttribute("votes_count", this.startupService.votesCount(id));
		return "startupdetails";
	}

	@RequestMapping(value = "/startupdetails/{id}", method = RequestMethod.POST)
	public String startupInvest(@RequestParam("investment") int investment, @PathVariable("id") long startupId, HttpServletRequest request ){
		if (investment <= 0){
			return "redirect:/allstartups";
		}
		if(userService.isAuthenticated(request)){
			this.startupService.invest(startupId, investment);
			return "redirect:/allstartups";
		}
		else{
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/startupdetails/sendforapprove/{id}")
	public String sendForApprove(@PathVariable("id") long id){
		this.startupService.ready(id);
		return "redirect:/startupdetails/{id}";
	}

	@RequestMapping(value = "/startupdetails/approve/{id}")
	public String approve(@PathVariable("id") long id){
		this.startupService.approve(id);
		return "redirect:/startupdetails/{id}";
	}

	@RequestMapping(value = "/startupdetails/{id}")
	public String reject(@PathVariable("id") long id){
		this.startupService.reject(id);
		return "redirect:/startupdetails/{id}";
	}

	@RequestMapping(value = "/startupdetails/delete/{id}")
	public String delete(@PathVariable("id") long id){
		this.startupService.deleteById(id);
		return "redirect:/allstartups";
	}

	@RequestMapping(value = "/startupdetails/close/{id}")
	public String close(@PathVariable("id") long id){
		this.startupService.close(id);
		return "redirect:/startupdetails/{id}";
	}


	@RequestMapping(value = "/error")
	public  String errorPage(){
		return "error";
	}
}
