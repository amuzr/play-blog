package com.amuzr.play.web;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.amuzr.play.domain.AddOption;
import com.amuzr.play.domain.Blog;
import com.amuzr.play.domain.Category;
import com.amuzr.play.domain.Tag;
import com.amuzr.play.domain.UploadItem;
import com.amuzr.play.domain.User;
import com.amuzr.play.service.BlogService;
import com.amuzr.play.service.CategoryService;
import com.amuzr.play.web.security.LoginInfo;

@Controller
@RequestMapping("/post")
@SessionAttributes("blog")
public class PostController {
	private BlogService blogService;
	private CategoryService categoryService;
	
	@Inject 
	private Provider<LoginInfo> loginInfoProvider;

	@Autowired
	public void init(BlogService blogService, CategoryService categoryService) {
		this.blogService = blogService;
		this.categoryService = categoryService;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields("category","subject","content");
	}
	
	@ModelAttribute("currentUser")
	public User currentUser() {
		return loginInfoProvider.get().getCurrentUser();
	}
	
	@ModelAttribute
	public List<Category> categories() {
		return this.categoryService.getAll();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String showform(ModelMap model){
		Blog blog = new Blog();
		blog.setUser(currentUser());
		model.addAttribute(blog);
		return "blog/post";
	}
	
	@RequestMapping(value="/category", method=RequestMethod.POST)
	@ResponseBody
	public AddOption addCate(@RequestParam("cateName") String cate_name) {
		Category category = new Category();
		category.setName(cate_name);
		this.categoryService.add(category);
		return new AddOption(String.valueOf(category.getId()),cate_name);
	}
	
	@RequestMapping(value="/tag", method=RequestMethod.POST)
	@ResponseBody
	public String addTag(@ModelAttribute Blog blog, @RequestParam("tagName") String tag_name) {
		Tag tag = new Tag();
		tag.setName(tag_name);
		tag.setBlog(blog);
		if(blog.addTag(tag))
			return "<button id=\""+tag_name+"\" onclick=\"removeTag(this)\">"+tag_name+" &times;</button>";
		else
			return "";
	}
	
	@RequestMapping(value="/delete_tag", method=RequestMethod.POST)
	@ResponseBody
	public String removeTag(@ModelAttribute Blog blog, @RequestParam("tagName") String tag_name) {
		Set<Tag> tags = blog.getTags();
		Tag tag = null;
		Boolean hasTag = false;
		for(Iterator<Tag> it = tags.iterator(); it.hasNext();){
			tag = it.next();
			if(tag_name.equals(tag.getName())) {
				hasTag = true;
				break;
			}
		}
		
		if(hasTag){
			blog.removeTag(tag);
			return "#"+tag_name;
		}else
			return "";
	}
	
	@RequestMapping(value="/file", method=RequestMethod.POST)
	@ResponseBody
	public UploadItem uploadItem(@RequestParam("file") MultipartFile mfile, HttpServletRequest request) {
		String root = request.getSession().getServletContext().getRealPath("/");
		
		UploadItem uploadItem = new UploadItem(mfile.getOriginalFilename());
		
		if(!mfile.isEmpty() && uploadItem.checkType()){
			File file = new File(uploadItem.getFullPath(root));
			try {
				mfile.transferTo(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			uploadItem.setFilelink("");
		}

		return uploadItem;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String post(@ModelAttribute @Valid Blog blog, BindingResult result, SessionStatus status) {
		if(result.hasErrors()) {
			return "blog/post";
		}
		else {
			this.blogService.add(blog);
			status.setComplete();
			return "redirect:blog";
		}
	}
	
}