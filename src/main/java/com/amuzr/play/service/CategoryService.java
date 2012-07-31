package com.amuzr.play.service;

import com.amuzr.play.dao.GenericDao;
import com.amuzr.play.domain.Category;

public interface CategoryService extends GenericDao<Category> {
	public Category findByName(String name);
}
