package model;

import javax.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name= "Eintrag")
public class Eintrag {

	@Id
	@GeneratedValue
	@Column(name= "id")
	private String id;

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

	@JoinColumn(name= "tag_name")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Tag.class)
	private Tag tag;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getModifikationDate() {
		return modifikationDate;
	}

	public void setModifikationDate(Date modifikationDate) {
		this.modifikationDate = modifikationDate;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTags(Tag tag) {
		this.tag = tag;
	}
}
