package com.honeacademy.webclientexample.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Post {
	private String title;
	
	private String body;
	
	private Long userId;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
}
