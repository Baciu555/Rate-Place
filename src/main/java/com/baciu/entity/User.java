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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(exclude = "opinions")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

}
