package com.amuzr.play.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amuzr.play.dao.TagDao;
import com.amuzr.play.domain.Pager;
import com.amuzr.play.domain.Tag;

@Service
@Transactional
public class TagServiceImpl implements TagService {
	
	private TagDao tagDao;
	
	@Autowired
	public void setTagDao(TagDao tagDao) {
		this.tagDao = tagDao;
	}

	public Tag add(Tag tag) {
		return tagDao.add(tag);
	}

	public Tag update(Tag tag) {
		return tagDao.update(tag);
	}

	public void delete(int id) {
		tagDao.delete(id);
	}

	@Transactional(readOnly=true)
	public Tag get(int id) {
		return tagDao.get(id);
	}

	@Transactional(readOnly=true)
	public List<Tag> getAll() {
		return tagDao.getAll();
	}
	
	@Transactional(readOnly=true)
	public List<Tag> findByName(String name, Pager pager) {
		return tagDao.findByName(name,pager);
	}

}
