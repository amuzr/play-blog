package com.amuzr.play.web;

import javax.inject.Inject;
import javax.inject.Provider;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amuzr.play.web.security.LoginInfo;

@Controller
@RequestMapping
public class MainController {
	private @Inject Provider<LoginInfo> loginInfoProvider;
	
	@RequestMapping("/welcome")
	public void welcome() {
	}
	
	@RequestMapping("/logout")
	public String logout() {
		loginInfoProvider.get().remove();
		return "redirect:blog";
	}
	
	@RequestMapping("/accessdenied")
	public String notlogin() {
		return "/accessdenied";
	}
}
