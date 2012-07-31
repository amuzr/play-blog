package com.amuzr.play.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.amuzr.play.domain.Pager;
import com.amuzr.play.domain.Tag;

@Repository
public class TagDaoImpl implements TagDao {
	
	@PersistenceContext EntityManager em;

	public Tag add(Tag tag) {
		em.persist(tag);
		return tag;
	}

	public Tag update(Tag tag) {
		throw new UnsupportedOperationException();
	}

	public void delete(int id) {
		em.remove(get(id));
	}

	public Tag get(int id) {
		throw new UnsupportedOperationException();
	}

	public List<Tag> getAll() {
		return em.createQuery("select t from Tag t", Tag.class).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Tag> findByName(String name, Pager pager) {
		long count = (Long) em.createQuery("select count(t) from Tag t where name= :name").setParameter("name", name).getSingleResult();
		pager.setResults((int)count);
		pager.init();
		int firstCursor=(pager.getCurPage()-1)*pager.getBlogPerPage();
		Query q = em.createQuery("select t from Tag t where name= :name", Tag.class).setParameter("name", name);
			q.setFirstResult(firstCursor);
			q.setMaxResults(pager.getBlogPerPage());
		return q.getResultList();
	}

}
