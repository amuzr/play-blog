package com.amuzr.play.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amuzr.play.dao.UserDao;
import com.amuzr.play.domain.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public User add(User user) {
		return userDao.add(user);
	}

	public User update(User user) {
		return userDao.update(user);
	}

	public void delete(int id) {
		userDao.delete(id);		
	}
	
	public int deleteAll() {
		return userDao.deleteAll();
	}

	public User get(int id) {
		return userDao.get(id);
	}
	
	public List<User> getAll() {
		return userDao.getAll();
	}
	
	public User findUser(String email) {
		return userDao.findUser(email);
	}
	
	public void login(User user) {
		this.userDao.update(user);
	}

}
