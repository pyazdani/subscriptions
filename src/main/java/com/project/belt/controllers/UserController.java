package com.project.belt.controllers;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.belt.models.Subscription;
import com.project.belt.models.User;
import com.project.belt.services.RoleService;
import com.project.belt.services.SubscriptionService;
import com.project.belt.services.UserService;
import com.project.belt.validations.UserValidator;

@Controller
public class UserController {
	private UserService userService;
	private RoleService roleService;
	private SubscriptionService subscriptionService;
	private UserValidator userValidator;
	
	public UserController(UserService userService, RoleService roleService, SubscriptionService subscriptionService, UserValidator userValidator){
		this.userService= userService;
		this.roleService = roleService;
		this.subscriptionService= subscriptionService;
		this.userValidator = userValidator;
	}
	
// TO VIEW MAIN LOG IN AND REGISTRATION PAGE --LOGREG.JSP
	@RequestMapping()
	public String logReg(@Valid @ModelAttribute("user")User user, @RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout,  Model model){
		if(error != null) {
            model.addAttribute("errorMessage", "Invalid Credentials.");
        }
        if(logout != null) {
            model.addAttribute("logoutMessage", "Logout Successfull!");
        }
		return "logReg.jsp";
	}

// TO REGISTER NEW USER 
	@RequestMapping("/register")
	public String register(@Valid @ModelAttribute("user")User user, BindingResult result){
		userValidator.validate(user, result);		
		if(result.hasErrors()){
			return "logReg.jsp";
		}	
		if(roleService.findByName("ROLE_ADMIN").getUsers().size()<1){
			userService.saveWithAdminRole(user);	
		} else{
			userService.saveWithUserRole(user);	
		}	
		return "redirect:/reroute";
	}
		
	@RequestMapping("/selection")
	public String selection(Principal principal, @ModelAttribute ("subscription") Subscription subscription, BindingResult result, Model model){
		model.addAttribute("subscriptions", subscriptionService.all());
		String email= principal.getName();
		User user= userService.findByEmail(email);
		model.addAttribute("currentUser", user);
		return "selection.jsp";
	}

	
// AFTER POSTMAPPING FROM LOGIN (BACK-END) TO DETERMIN ADMIN OR USER-- THEN REROUTE
	@RequestMapping(value={"/"})
	public String reroute(Principal principal, Model model){
		String email= principal.getName();
		User user= userService.findByEmail(email);

		userService.update(user);
		if (user.isAdmin()){
			return "redirect:/admin";
		} else {
			return "redirect:/selection";
		}
	}

	@RequestMapping("/admin")
	public String admin(Principal principal, Model model, @ModelAttribute("subscription") Subscription subscription){
		String email= principal.getName();
		User user= userService.findByEmail(email);
		
		model.addAttribute("currentUser", user);
		model.addAttribute("allUsers", userService.all());
		model.addAttribute("allSubscriptions", subscriptionService.all());		
		model.addAttribute("subscriptions", subscriptionService.all());
		return "admin.jsp";
	}
	
	@RequestMapping("/profile")
	public String profile(Principal principal, Model model){
		String email= principal.getName();
		User user= userService.findByEmail(email);
		userService.update(user);
		model.addAttribute("subscriptions", user.getSubscriptions());
		model.addAttribute("currentUser", user);
		return "profile.jsp";
	}
	
	@PostMapping("/addPackage")
	public String addPackage(@Valid @ModelAttribute("subscription") Subscription subscription, BindingResult result){
		subscriptionService.create(subscription);
		return "redirect:/admin";
	}
	
	
	@PostMapping("/signup/{id}")
	public String signup(@PathVariable("id") Long id,@ModelAttribute("subscription") Subscription subscription, BindingResult result){
		
		String subId= (String)result.getFieldValue("subscriptionName");
		Subscription itsSubscription= subscriptionService.findById(Long.parseLong(subId.trim()));
		
		User user= userService.findById(id);
		List<Subscription> list= user.getSubscriptions();
		String dueDate= (String)result.getFieldValue("due");
		int due = Integer.parseInt(dueDate);
		itsSubscription.setDue(due);
		list.add(itsSubscription);
		user.setSubscriptions(list);


		subscriptionService.create(itsSubscription);

		return "redirect:/profile";
	}

	@RequestMapping(value= {"/admin/activate/{id}", "/admin/deactivate/{id}"})
	public String deactivate(@ModelAttribute("subscription") Subscription subscription, @PathVariable ("id") Long id){
		boolean curStatus = subscriptionService.findById(id).isStatus();
		
		if(curStatus == true){
			curStatus = false;
		} else {
			curStatus = true;
		}
		subscriptionService.findById(id).setStatus(curStatus);
		subscriptionService.update(subscriptionService.findById(id));
		return "redirect:/admin";
	}

	@RequestMapping("admin/delete/{id}")
	public String delete(@ModelAttribute("subscription") Subscription subscription, @PathVariable("id") Long id) {		
		if(subscription.getUsers().size() < 1) {
			subscriptionService.delete(id);
		}
		return "redirect:/admin";
	}
}
	