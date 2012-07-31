package com.amuzr.play.service;

import java.util.List;

import com.amuzr.play.domain.User;

public interface UserService extends GenericService<User> {
	public int deleteAll();
	public List<User> getAll();
	public User findUser(String email);
	public void login(User user);
}
