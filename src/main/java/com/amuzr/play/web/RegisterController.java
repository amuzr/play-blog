package com.amuzr.play.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.amuzr.play.domain.User;
import com.amuzr.play.service.UserService;
import com.amuzr.play.web.validator.UsernameValidator;

@Controller
@RequestMapping("/register")
@SessionAttributes("user")
public class RegisterController {
	private UserService userService;
	private UsernameValidator usernameValidator;
	
	@Autowired
	public void init(UserService userService, UsernameValidator usernameValidator) {
		this.userService = userService;
		this.usernameValidator = usernameValidator;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setDisallowedFields("id");
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String showform(ModelMap model) {
		User user = new User();
		model.addAttribute(user);
		return "user/register";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String register(@ModelAttribute @Valid User user, BindingResult result, SessionStatus status) {
		this.usernameValidator.validate(user, result);
		if (result.hasErrors()) {
			return "user/register";
		}
		else {
			this.userService.add(user);				
			status.setComplete();
			return "redirect:login";
		}
	}
}
