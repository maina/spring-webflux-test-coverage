package com.honeacademy.webclientexample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.honeacademy.webclientexample.model.Post;
import com.honeacademy.webclientexample.utils.WebClientUtil;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/posts")
public class PostController {

	@Value("${webclientexample.postsapi.host}")
	String baseUrl;

	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Mono<Post> createPost(@RequestBody final Post request) {
		return WebClientUtil.add(WebClientUtil.getWebClient(baseUrl), "", builder -> builder.path("/posts").build(),
				request, Post.class);
	}

	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Mono<Post> updatePost(@RequestBody final Post request,@PathVariable Long id) {
		return WebClientUtil.update(WebClientUtil.getWebClient(baseUrl), "",
				builder -> builder.path("/posts/"+id).build(), request, Post.class);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public Mono<Post> deletePost(@RequestBody final Post request,@PathVariable Long id) {
		return WebClientUtil.delete(WebClientUtil.getWebClient(baseUrl), "",
				builder -> builder.path("/posts/"+id).build(), Post.class);
	}

	@GetMapping("")
	@ResponseStatus(code = HttpStatus.OK)
	public Mono<List<Post>> listPosts() {
		ParameterizedTypeReference<List<Post>> parameterizedTypeReference =
		        new ParameterizedTypeReference<List<Post>>() {};
		return WebClientUtil.fetchList(WebClientUtil.getWebClient(baseUrl), "", parameterizedTypeReference, builder -> builder.path("/posts").build());
	}

	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Mono<Post> getPost(@PathVariable Long id) {
		return WebClientUtil.fetchSingleObject(WebClientUtil.getWebClient(baseUrl), "", builder -> builder.path("/posts/" + id).build(), Post.class);
	}

}
