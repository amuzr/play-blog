package com.amuzr.play.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amuzr.play.dao.CategoryDao;
import com.amuzr.play.domain.Category;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	
	private CategoryDao categoryDao;
	
	@Autowired
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public Category add(Category category) {
		return categoryDao.add(category);
	}

	public Category update(Category category) {
		return categoryDao.update(category);
	}

	public void delete(int id) {
		categoryDao.delete(id);
	}

	public Category get(int id) {
		return categoryDao.get(id);
	}
	
	public Category findByName(String name) {
		return categoryDao.findByName(name);
	}

	public List<Category> getAll() {
		return categoryDao.getAll();
	}

}
