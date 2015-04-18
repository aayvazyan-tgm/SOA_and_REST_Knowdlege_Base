package model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name= "Author")
public class Author {

	@Id
	private String email;

	@Column
	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date dateJoined;

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "author_eintrag")
	private Set<Eintrag> eintrag;

	public Author() {
		dateJoined= new Date();
	}

	public Date getDateJoined() {
		return dateJoined;
	}

	public Set<Eintrag> getEintrag() {
		return eintrag;
	}

	public void setEintrag(Set<Eintrag> eintrag) {
		this.eintrag = eintrag;
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

}
