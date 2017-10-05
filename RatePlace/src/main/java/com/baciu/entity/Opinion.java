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

@Entity
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

	public Opinion() {
	}

	public Opinion(Integer id, String content, Date entryDate, Integer grade) {
		super();
		this.id = id;
		this.content = content;
		this.entryDate = entryDate;
		this.grade = grade;
	}

	public Opinion(Integer id, User user, Place place, String content, Date entryDate, Integer grade) {
		super();
		this.id = id;
		this.user = user;
		this.place = place;
		this.content = content;
		this.entryDate = entryDate;
		this.grade = grade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Opinion [id=" + id + ", opinion=" + content + ", entryDate="
				+ entryDate + ", grade=" + grade + "]";
	}

}
