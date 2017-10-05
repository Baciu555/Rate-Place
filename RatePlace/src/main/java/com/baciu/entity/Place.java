package com.baciu.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "place", catalog = "inzynierski")
public class Place implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Size(min=1, max=50, message = "nazwa powinna miec dlugosc od 1 do 50 znakow")
	@Column(name = "name")
	private String name;

	@NotEmpty(message = "pole nie moze zostac puste")
	@Column(name = "description")
	private String description;

	@NotNull(message = "pole nie moze zostac puste")
	@DecimalMin("-180.0")
	@DecimalMax("180.0")
	@Column(name = "longitude")
	private Double longitude;

	@NotNull(message = "pole nie moze zostac puste")
	@DecimalMin("-90.0")
	@DecimalMax("90.0")
	@Column(name = "latitude")
	private Double latitude;

	@NotNull(message = "musisz wybrac typ")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id", nullable = false)
	@JsonBackReference
	private Type type;

	@Column(name = "image_path")
	private String imagePath;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "place")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JsonManagedReference
	private Set<Opinion> opinions = new HashSet<Opinion>(0);
	
	@Formula("(SELECT coalesce(avg(o.grade), 0) FROM Opinion o WHERE o.place_id = id)")
	private Double avgGrade;

	public Place() {
	}

	public Place(Integer id, String name, String description, Double longitude, Double latitude, Type type,
			String imagePath, Set<Opinion> opinions) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.longitude = longitude;
		this.latitude = latitude;
		this.type = type;
		this.imagePath = imagePath;
		this.opinions = opinions;
	}

	public Place(Integer id, String name, String description, Double longitude, Double latitude, String imagePath) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.longitude = longitude;
		this.latitude = latitude;
		this.imagePath = imagePath;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Set<Opinion> getOpinions() {
		return opinions;
	}

	public void setOpinions(Set<Opinion> opinions) {
		this.opinions = opinions;
	}

	public Double getAvgGrade() {
		return avgGrade;
	}

	@Override
	public String toString() {
		return "Place [id=" + id + ", name=" + name + ", description=" + description + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", type=" + type + ", imagePath=" + imagePath + "]";
	}

}
