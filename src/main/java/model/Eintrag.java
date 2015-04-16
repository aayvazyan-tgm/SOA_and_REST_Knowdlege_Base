package model;

import org.hibernate.annotations.Parent;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name= "Eintrag")
public class Eintrag {

	@Id
	@GeneratedValue
	private Integer id;

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
	private byte[] file;

	@Column
	private boolean deleted;

	@PreUpdate
	protected void onUpdate() {
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

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getModifikationDate() {
		return modifikationDate;
	}

	public void setModifikationDate(Date modifikationDate) {
		this.modifikationDate = modifikationDate;
	}
}
