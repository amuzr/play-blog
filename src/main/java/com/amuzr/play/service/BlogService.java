package com.amuzr.play.service;

import java.util.List;

import com.amuzr.play.domain.Blog;
import com.amuzr.play.domain.Category;
import com.amuzr.play.domain.Pager;

public interface BlogService extends GenericService<Blog> {
	List<Blog> getAll(Pager pager);
	List<Blog> findByCategory(Category category,Pager pager);
	List<Blog> findByArchive(String archive,Pager pager);
	List<String> getArchivePerMonth();
}
