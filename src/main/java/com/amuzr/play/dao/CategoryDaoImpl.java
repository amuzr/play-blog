package com.amuzr.play.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.amuzr.play.domain.Category;

@Repository
public class CategoryDaoImpl implements CategoryDao {
	
	@PersistenceContext EntityManager em;

	public Category add(Category category) {
		em.persist(category);
		return category;
	}

	public Category update(Category category) {
		em.merge(category);
		return category;
	}

	public void delete(int id) {
		em.remove(get(id));
	}

	public Category get(int id) {
		return em.find(Category.class, id);
	}
	
	public Category findByName(String name) {
		return (Category)em.createQuery("select c from Category c where name = :name", Category.class).setParameter("name", name).getSingleResult();
	}

	public List<Category> getAll() {
		return em.createQuery("select c from Category c", Category.class).getResultList(); 
	}

}
