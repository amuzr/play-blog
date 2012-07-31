package com.amuzr.play.web.security;

import java.util.Date;

import com.amuzr.play.domain.User;

public interface LoginInfo {
	void save(User user);
	void remove();
	boolean isLoggedIn();
	User getCurrentUser();
	Date getLoginTime();
}
