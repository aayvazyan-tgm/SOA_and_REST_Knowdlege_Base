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
	private Integer id;

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

	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name="EINTRAG_TAG",
			joinColumns={@JoinColumn(name="id")},
			inverseJoinColumns={@JoinColumn(name="tag_id")})
	private Set<Tag> tags= new HashSet<Tag>();

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

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public Integer getId() {
		return  id;
	}

	public void setId(Integer id) {
		this. id = id;
	}
}
