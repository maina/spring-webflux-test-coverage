package com.honeacademy.webclientexample.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "body", nullable = false)
	private String body;
	
	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", nullable = false)
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	private Date lastUpdatedDate;

}
