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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.amuzr.play.util.AmuzrUtil;

@Entity
@Table(name="User")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private int id;
	
	@Column(name="user_email")
	@Pattern(regexp="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	private String email;
	
	@Column(name="user_name")
	@Size(min=4, max=10)
	private String name;
	
	@Column(name="user_password")
	@NotNull
	private String password;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "user", cascade={CascadeType.ALL})
	private Set<Blog> blogs;
	
	public User() {
	}
	
	public User(int id, String email, String name, String password) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.password = password;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		if(password != "" || !password.isEmpty())
			this.password = AmuzrUtil.Md5String(password);
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

	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", password=" + password
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

}
