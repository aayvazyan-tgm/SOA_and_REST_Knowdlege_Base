package model;

import javax.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name= "Eintrag")
public class Eintrag {

	@Id
	@Column(name= "title")
	private String title;

	@JoinColumn(name= "email")
	@ManyToOne
	private Author author;

	@Column(updatable = false, nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifikationDate;

	@Column
	private boolean deleted;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Tag.class)
	@JoinTable(name= "eintrag_tag", joinColumns= { @JoinColumn(name = "title") }, inverseJoinColumns = { @JoinColumn(name = "tag_name") })
	private Set<Tag> tag;

	@PreUpdate
	public void onUpdate() {
		modifikationDate = new Date();
	}

	public Eintrag() {
		creationDate= new Date();
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getModifikationDate() {
		return modifikationDate;
	}

	public void setModifikationDate(Date modifikationDate) {
		this.modifikationDate = modifikationDate;
	}

	public Set<Tag> getTag() {
		return tag;
	}

	public void setTags(Set<Tag> tag) {
		this.tag = tag;
	}
}
