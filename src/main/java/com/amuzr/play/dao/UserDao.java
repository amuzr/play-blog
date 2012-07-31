package com.amuzr.play.dao;

import com.amuzr.play.domain.User;

public interface UserDao extends GenericDao<User> {
	public int deleteAll();
	public User findUser(String email);

}
