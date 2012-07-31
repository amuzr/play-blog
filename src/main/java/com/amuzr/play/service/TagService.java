package com.amuzr.play.service;

import java.util.List;

import com.amuzr.play.domain.Pager;
import com.amuzr.play.domain.Tag;

public interface TagService extends GenericService<Tag> {
	public List<Tag> getAll();
	public List<Tag> findByName(String name, Pager pager);
}
