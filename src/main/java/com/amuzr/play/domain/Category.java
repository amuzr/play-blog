package com.amuzr.play.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Category")
public class Category implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="category_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	
	@Column(name="category_name")
	String name;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="category", cascade={CascadeType.MERGE,CascadeType.REMOVE})
	Set<Blog> blogs;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Blog> getBlogs() {
		if(blogs==null)
			blogs = new HashSet<Blog>();
		return blogs;
	}

	public void setBlogs(Set<Blog> blogs) {
		this.blogs = blogs;
	}
	
	public void addBlog(Blog blog) {
		if (this.blogs == null || this.blogs.isEmpty())
		{
			this.blogs = new HashSet<Blog>();
		}
		this.blogs.add(blog);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
