package com.amuzr.play.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.amuzr.play.domain.Blog;
import com.amuzr.play.domain.Category;
import com.amuzr.play.domain.Pager;
import com.amuzr.play.domain.Tag;
import com.amuzr.play.domain.User;
import com.amuzr.play.service.BlogService;
import com.amuzr.play.service.CategoryService;
import com.amuzr.play.service.TagService;
import com.amuzr.play.web.security.LoginInfo;

@Controller
@Transactional
public class BlogController {
	
	private BlogService blogService;
	private CategoryService categoryService;
	private TagService tagService;
	
	@Inject 
	private Provider<LoginInfo> loginInfoProvider;
	
	@Autowired
	public void init(BlogService blogService, CategoryService categoryService, TagService tagService) {
		this.blogService = blogService;
		this.categoryService = categoryService;
		this.tagService = tagService;
	}
	
	@ModelAttribute("currentUser")
	public User currentUser() {
		return loginInfoProvider.get().getCurrentUser();
	}
	
	@ModelAttribute
	public List<Category> categories() {
		return this.categoryService.getAll();
	}
	
	@ModelAttribute("archiveList")
	public List<String> archives() {
		return this.blogService.getArchivePerMonth();
	}
	
	@RequestMapping(value="/blog", method=RequestMethod.GET)
	public String pagedList(@RequestParam(value="p", required=false, defaultValue = "1") int movPage, Pager pager, ModelMap model){
		pager.setCurPage(movPage);
		model.addAttribute(this.blogService.getAll(pager));
		return "blog/list";
		
	}

	@RequestMapping("/blog/category/{name}")
	public String listByCategory(@PathVariable String name,
			@RequestParam(value="p", required=false, defaultValue = "1") int movPage, Pager pager, ModelMap model) {
		pager.setCurPage(movPage);
		Category category = this.categoryService.findByName(name);
		model.addAttribute(this.blogService.findByCategory(category,pager));
		return "blog/list";
	}
	
	@RequestMapping("/blog/archive/{year}/{month}")
	public String listByArchive(@PathVariable String year, @PathVariable String month,
			@RequestParam(value="p", required=false, defaultValue = "1") int movPage, Pager pager, ModelMap model) {
		pager.setCurPage(movPage);
		String name = year+"/"+month;
		model.addAttribute(this.blogService.findByArchive(name,pager));
		return "blog/list";
	}
	
	@RequestMapping("/blog/tag/{name}")
	public String listByTag(@PathVariable String name,
			@RequestParam(value="p", required=false, defaultValue = "1") int movPage, Pager pager, ModelMap model) {
		pager.setCurPage(movPage);
		List<Tag> tags = this.tagService.findByName(name,pager);
		List<Blog> blogs = new ArrayList<Blog>();
		for(Iterator<Tag> it = tags.iterator(); it.hasNext();){
			blogs.add(it.next().getBlog());
		}
		model.addAttribute(blogs);
		return "blog/list";
	}
	
	@RequestMapping("/blog/{id}")
	public String list(@PathVariable int id, ModelMap model) {
		model.addAttribute(this.blogService.get(id));
		model.addAttribute("id", id);
		return "blog/view";
	}
	
	
	@RequestMapping("/blog/delete/{id}")
	public String delete(@PathVariable int id) {
		this.blogService.delete(id);
		return "redirect:/blog";
	}

}
