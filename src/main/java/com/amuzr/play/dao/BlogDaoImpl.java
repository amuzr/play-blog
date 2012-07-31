package com.amuzr.play.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.amuzr.play.domain.Blog;
import com.amuzr.play.domain.Category;
import com.amuzr.play.domain.Pager;

@Repository
public class BlogDaoImpl implements BlogDao {
	
	@PersistenceContext EntityManager em;
	
	public Blog add(Blog blog) {
		em.persist(blog);
		return blog;
	}

	public Blog update(Blog blog) {
		return em.merge(blog);
	}

	public void delete(int id) {
		em.remove(get(id));
	}

	public Blog get(int id) {
		return em.find(Blog.class, id);
	}
	
	public List<Blog> getAll() {
		return em.createQuery("select b from Blog b order by b.id desc", Blog.class).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Blog> getBlogPages(Pager pager) {
		long count = (Long) em.createQuery("select count(b) from Blog b").getSingleResult();
		pager.setResults((int)count);
		pager.init();
		int firstCursor=(pager.getCurPage()-1)*pager.getBlogPerPage();

		Query q = em.createQuery("select b from Blog b order by b.id desc");
			q.setFirstResult(firstCursor);
			q.setMaxResults(pager.getBlogPerPage());
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Blog> findByCategory(Category category, Pager pager) {
		long count = (Long) em.createQuery("select count(b) from Blog b where b.category = :category").setParameter("category", category).getSingleResult();
		pager.setResults((int)count);
		pager.init();
		int firstCursor=(pager.getCurPage()-1)*pager.getBlogPerPage();
		
		Query q = em.createQuery("select b from Blog b where b.category = :category order by b.id desc", Blog.class).setParameter("category", category);
			q.setFirstResult(firstCursor);
			q.setMaxResults(pager.getBlogPerPage());
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Blog> findByArchive(String archive, Pager pager) {
		long count = (Long) em.createQuery("select count(b) from Blog b where b.archive = :archive").setParameter("archive", archive).getSingleResult();
		pager.setResults((int)count);
		pager.init();
		int firstCursor=(pager.getCurPage()-1)*pager.getBlogPerPage();
		
		Query q = em.createQuery("select b from Blog b where b.archive = :archive order by b.id desc", Blog.class).setParameter("archive", archive);
			q.setFirstResult(firstCursor);
			q.setMaxResults(pager.getBlogPerPage());
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getArchivePerMonth() {
		return em.createQuery("select b.archive from Blog b group by b.archive").getResultList();
	}

}
