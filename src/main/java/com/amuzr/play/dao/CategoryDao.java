package com.amuzr.play.dao;

import com.amuzr.play.domain.Category;

public interface CategoryDao extends GenericDao<Category> {
	public Category findByName(String name);
}
