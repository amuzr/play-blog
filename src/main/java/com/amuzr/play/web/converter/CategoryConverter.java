package com.amuzr.play.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.amuzr.play.domain.Category;
import com.amuzr.play.service.CategoryService;

public class CategoryConverter {
	private CategoryConverter() {}
	
	public static class CategoryToString implements Converter<Category, String> {
		public String convert(Category category) {
			return (category == null) ? "" : String.valueOf(category.getId());
		}
	}
	
	public static class StringToCategory implements Converter<String, Category> {
		private CategoryService categoryService;
		
		@Autowired
		public void setCategoryService(CategoryService categoryService) {
			this.categoryService = categoryService;
		}
		
		public Category convert(String text) {
			return categoryService.get(Integer.valueOf(text));
		}
		
	}

}
