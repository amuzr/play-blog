package com.amuzr.play.web.validator;

import javax.inject.Inject;
import javax.inject.Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.amuzr.play.domain.Login;
import com.amuzr.play.domain.User;
import com.amuzr.play.service.UserService;
import com.amuzr.play.web.security.LoginInfo;

@Component
public class LoginValidator implements Validator {
	private UserService userService;
	
	@Inject
	private Provider<LoginInfo> loginInfoProvider;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public boolean supports(Class<?> clazz) {
		return Login.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Login login = (Login)target;
		User user = userService.findUser(login.getEmail());
		if (user == null || !user.getPassword().equals(login.getPassword())) {
			errors.reject("invalidLogin", "Invalid Login");
		}
		else {
			LoginInfo loginInfo = loginInfoProvider.get();
			loginInfo.save(user);
		}
	}
}
