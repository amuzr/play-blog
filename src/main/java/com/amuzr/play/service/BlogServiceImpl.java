package com.amuzr.play.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amuzr.play.dao.BlogDao;
import com.amuzr.play.domain.Blog;
import com.amuzr.play.domain.Category;
import com.amuzr.play.domain.Pager;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {

	private BlogDao blogDao;
	
	@Autowired
	public void setBlogDao(BlogDao blogDao) {
		this.blogDao = blogDao;
	}

	public Blog add(Blog blog) {
		blog.initDates();
		return blogDao.add(blog);
	}
	
	public Blog update(Blog blog) {
		return blogDao.update(blog);
	}

	public void delete(int id) {
		blogDao.delete(id);
	}

	@Transactional(readOnly=true)
	public Blog get(int id) {
		return blogDao.get(id);
	}
	
	@Transactional(readOnly=true)
	public List<Blog> getAll(Pager pager) {
		return blogDao.getBlogPages(pager);
	}
	
	@Transactional(readOnly=true)
	public List<Blog> findByCategory(Category category, Pager pager) {
		return blogDao.findByCategory(category,pager);
	}

	@Transactional(readOnly=true)
	public List<Blog> findByArchive(String archive, Pager pager) {
		return blogDao.findByArchive(archive,pager);
	}

	@Transactional(readOnly=true)
	public List<String> getArchivePerMonth() {
		return blogDao.getArchivePerMonth();
	}

}
