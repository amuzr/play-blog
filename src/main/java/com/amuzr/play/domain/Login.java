package com.amuzr.play.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.amuzr.play.util.AmuzrUtil;

public class Login {
	@Pattern(regexp="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	String email;
	@NotNull
	String password;

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		if(password != "" || !password.isEmpty())		
			this.password = AmuzrUtil.Md5String(password);
	}
}
