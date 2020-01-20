package com.honeacademy.webclientexample.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.honeacademy.webclientexample.dto.PostDto;
import com.honeacademy.webclientexample.model.Post;
import com.honeacademy.webclientexample.repository.PostRepository;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
	private final PostRepository postRepository;

	public Mono<Post> savePost(PostDto postDto) {
		log.debug("saving post");
		Post post = new Post();
		post.setBody(postDto.getBody());
		post.setCreatedDate(new Date());
		post.setTitle(postDto.getTitle());
		post.setUserId(1l);

		return Mono.just(postRepository.save(post));

	}

	public Mono<Post> updatePost(PostDto postDto, Long id) throws Exception {
		log.debug("updating post with id" + id);
		Optional<Post> existingPost = postRepository.findById(id);
		if (!existingPost.isPresent()) {
			throw new NotFoundException(String.format("Post with id %d not found", id));
		}
		Post post = existingPost.get();
		post.setBody(postDto.getBody());
		post.setLastUpdatedDate(new Date());
		post.setTitle(postDto.getTitle());
		post.setUserId(1l);

		return Mono.just(postRepository.save(post));

	}
	public Mono<Post> getPostById(Long id) throws Exception {
		log.debug("get post with id" + id);
		Optional<Post> existingPost = postRepository.findById(id);
		if (!existingPost.isPresent()) {
			throw new NotFoundException(String.format("Post with id %d not found", id));
		}
		Post post = existingPost.get();


		return Mono.just(post);

	}

	public Mono<List<Post>> listPosts(int offset, int limit) {
		Pageable pageable = PageRequest.of(offset / limit, limit, Sort.by("createdDate").descending());
		return Mono.just(postRepository.findAll(pageable).getContent());

	}
	public Mono<Boolean> deletePost(Long id){
		postRepository.deleteById(id);
		return Mono.just(true);
	}

}
