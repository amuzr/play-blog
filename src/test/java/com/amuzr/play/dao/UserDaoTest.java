package com.amuzr.play.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.amuzr.play.domain.User;
import com.amuzr.play.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/applicationcontext.xml")
@Transactional
public class UserDaoTest {
	@PersistenceContext EntityManager em; 
	@Autowired UserService userService;
	User user1;
	User user2;
	User user3;
	
	@Before
	public void before() {
		userService.deleteAll();
		user1 = new User(1,"dev.amuzr@gmail.com", "user1", "pass1");
		user2 = new User(2,"test@naver.com", "user2", "pass2");
		user3 = new User(3,"abcd@naver.com", "user3", "pass3");
	}
	
	@Test
	public void crud() {
		userService.add(user1);
		userService.add(user2);
		userService.add(user3);
		assertThat(userService.get(user1.getId()).getName(), is("user1"));
		assertThat(userService.get(user2.getId()).getName(), is("user2"));
		assertThat(userService.get(user3.getId()).getName(), is("user3"));
		
		user1.setName("modified1");
		user1 = userService.update(user1);
		em.flush();
		User updatedUser1 = userService.get(user1.getId());
		assertThat(updatedUser1.getName(), is("modified1"));
		
		userService.delete(user1.getId());
		assertThat(userService.get(user1.getId()), is(nullValue()));
		
	}
	
	@Test
	public void getAll() {
		List<User> list = userService.getAll();
			for(User a : list)
				assertNotNull(a.getName());
	}
	
}