package com.amuzr.play.web;

import javax.inject.Inject;
import javax.inject.Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amuzr.play.domain.User;
import com.amuzr.play.service.UserService;
import com.amuzr.play.web.security.LoginInfo;

@Controller
@Transactional
public class UserController {
	private UserService userService;
	
	@Inject 
	private Provider<LoginInfo> loginInfoProvider;
	
	@Autowired
	public void init(UserService userService) {
		this.userService = userService;
	}
	
	@ModelAttribute("currentUser")
	public User currentUser() {
		return loginInfoProvider.get().getCurrentUser();
	}
	
	@RequestMapping("/user")
	public String showUserInfo(Model model) {
		this.userService.get(currentUser().getId());
		return "user/option";
	}
	
	@RequestMapping("/user/delete/{id}")
	public String delete(@PathVariable int id) {
		this.userService.delete(id);
		loginInfoProvider.get().remove();
		return "redirect:/blog";
	}
	

}
