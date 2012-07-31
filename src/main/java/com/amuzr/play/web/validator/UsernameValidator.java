package com.amuzr.play.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.amuzr.play.domain.User;
import com.amuzr.play.service.UserService;

@Component
public class UsernameValidator implements Validator {
	@Autowired 
	private UserService userService;

	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		User formUser = (User)target;
		User user = this.userService.findUser(formUser.getEmail());
		if (user != null && user.getId() != formUser.getId()) errors.rejectValue("email", "duplicateEmail");
	}

}
