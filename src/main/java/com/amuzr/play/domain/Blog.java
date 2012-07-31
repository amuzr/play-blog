package com.amuzr.play.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Blog")
public class Blog implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne(fetch=FetchType.EAGER, optional=false)
	@JoinColumn(name="category_id")
	@NotNull
	private Category category;
	
	@Column(name = "subject")
	@NotNull
	private String subject;
	
	@Column(name = "content",length=2000)
	@Size(min=2,max=2000)
	private String content;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE,},fetch=FetchType.EAGER, mappedBy = "blog", orphanRemoval = true)
	@OrderBy("name asc")
	private Set<Tag> tags;
	
	@Column(name = "created")
	@DateTimeFormat(style="F-")
	private Date created;
	
	@Column(name = "archive")
	private String archive;
	
	public Blog() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User author) {
		this.user = author;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set<Tag> getTags() {
		return tags;
	}
	
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public boolean addTag(Tag tag) {
		if (this.tags == null || this.tags.isEmpty())
		{
			this.tags = new HashSet<Tag>();
		}
		return this.tags.add(tag);
	}
	
	public boolean removeTag(Tag tag) {
		if (this.tags == null || this.tags.isEmpty()){
			return false;
		} else {
			return this.tags.remove(tag);
		}
			
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getArchive() {
		return archive;
	}

	public void setArchive(String archive) {
		this.archive = archive;
	}

	public String toString() {
		return "Blog [id=" + id + ", category=" +category.getName()+ "," +
				" user=" + user.getName()+ ", subject=" + subject+ "," +
				" content=" + content + ", created="	+ created + "]";
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
		Blog other = (Blog) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	public void initDates() {
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		if (this.created == null) this.created = now;
		if (this.archive == null) this.archive = String.format("%04d/%02d", cal.get(Calendar.YEAR), (cal.get(Calendar.MONTH)+1));
	}
	
}