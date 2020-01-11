package com.honeacademy.webclientexample.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PostDto {
	@NotEmpty
	@NotNull
	private String title;
	
	@NotEmpty
	@NotNull
	private String body;
	
}
