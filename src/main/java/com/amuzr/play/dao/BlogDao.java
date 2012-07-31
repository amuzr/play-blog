package com.amuzr.play.dao;

import java.util.List;

import com.amuzr.play.domain.Blog;
import com.amuzr.play.domain.Category;
import com.amuzr.play.domain.Pager;

public interface BlogDao extends GenericDao<Blog> {
	List<Blog> findByCategory(Category category, Pager pager);
	List<Blog> findByArchive(String archive, Pager pager);
	List<Blog> getBlogPages(Pager pager);
	List<String> getArchivePerMonth();
}
