package com.amuzr.play.web;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.amuzr.play.domain.Login;
import com.amuzr.play.service.UserService;
import com.amuzr.play.web.security.LoginInfo;
import com.amuzr.play.web.validator.LoginValidator;

@Controller
@RequestMapping("/login")
@SessionAttributes("login") 
public class LoginController {
	private LoginValidator loginValidator;
	private UserService userService;
	private Provider<LoginInfo> loginInfoProvider;

	@Inject 	
	public void setLoginInfoProvider(Provider<LoginInfo> loginInfoProvider) {
		this.loginInfoProvider = loginInfoProvider;
	}

	@Autowired
	public void init(LoginValidator loginValidator, UserService userService) {
		this.loginValidator = loginValidator;
		this.userService = userService;
	}

	@RequestMapping(method=RequestMethod.GET)
	public String showform(ModelMap model) {
		model.addAttribute(new Login());
		return "user/login";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String login(@ModelAttribute @Valid Login login, BindingResult result, SessionStatus status) {
		if (result.hasErrors()) return "user/login";
		
		this.loginValidator.validate(login, result);
		if (result.hasErrors()) {
			return "user/login";
		}
		else {
			userService.login(loginInfoProvider.get().getCurrentUser());
			status.setComplete();
			return "redirect:blog";
		}
	}
}
