package com.amuzr.play.dao;

import java.util.List;

import com.amuzr.play.domain.Pager;
import com.amuzr.play.domain.Tag;

public interface TagDao extends GenericDao<Tag> {
	public List<Tag> findByName(String name, Pager pager);
}
