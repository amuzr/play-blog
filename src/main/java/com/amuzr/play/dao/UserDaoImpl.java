package com.amuzr.play.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.amuzr.play.domain.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	@PersistenceContext EntityManager em;

	public User add(User author) {
		em.persist(author);
		return author;
	}

	public User update(User author) {
		return em.merge(author);
	}

	public void delete(int id) {
		em.remove(get(id));
	}

	public User get(int id) {
		return em.find(User.class, id);
	}

	public int deleteAll() {
		return em.createQuery("delete from User u").executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<User> getAll() {
		return em.createQuery("select u from User u").getResultList();
	}
	
	public User findUser(String email) {
		User user = null;
		try {
			user = (User)em.createQuery("select u from User u where u.email = :email", User.class).setParameter("email", email).getSingleResult(); 
		} catch (NoResultException nre){}
		
		return user; 
	}

}
