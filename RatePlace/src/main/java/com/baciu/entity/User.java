package com.baciu.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "users", catalog = "inyznierski")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Size(min=5, max=20, message = "nazwa powinna skladac sie od 5 do 20 znakow")
	@Column(name = "username", unique = true)
	private String username;

	@Size(min=5, max=50, message = "haslo powinno skladac sie od 5 do 20 znakow")
	@Column(name = "password")
	@JsonIgnore
	private String password;

	@NotEmpty(message = "pole nie moze zostac puste")
	@Email(message = "format emaila nieprawidlowy")
	@Column(name = "email", unique = true)
	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "register_date")
	private Date registerDate;

	@Column(name = "avatar_path")
	private String avatarPath;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	@JsonManagedReference
	private Set<Opinion> opinions = new HashSet<Opinion>(0);

	public User() {
	}

	public User(Integer id, String username, String password, String email, Date registerDate, String avatarPath,
			Set<Opinion> opinions) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.registerDate = registerDate;
		this.avatarPath = avatarPath;
		this.opinions = opinions;
	}

	public User(Integer id, String username, String password, String email, Date registerDate, String avatarPath) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.registerDate = registerDate;
		this.avatarPath = avatarPath;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getAvatarPath() {
		return avatarPath;
	}

	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}

	public Set<Opinion> getOpinions() {
		return opinions;
	}

	public void setOpinions(Set<Opinion> opinions) {
		this.opinions = opinions;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", registerDate=" + registerDate + ", avatarPath=" + avatarPath + "]";
	}

}
