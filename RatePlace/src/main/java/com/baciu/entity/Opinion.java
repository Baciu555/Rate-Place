package com.baciu.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(exclude={"place", "user"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "opinion", catalog = "inzynierski")
@SecondaryTable(name = "users")
public class Opinion implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	@JsonBackReference
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "place_id", nullable = false)
	@JsonBackReference
	private Place place;

	@Column(name = "content")
	@NotEmpty(message = "pole nie moze zostac puste")
	@Size(min = 2, message = "pole musi zawierac minimalnie 2 znaki")
	private String content;

	@Temporal(TemporalType.DATE)
	@Column(name = "entry_date")
	private Date entryDate;

	@Column(name = "grade")
	private Integer grade;

}
