package com.honeacademy.webclientexample.repository;

import org.springframework.data.repository.CrudRepository;

import com.honeacademy.webclientexample.model.Post;

public interface PostRepository extends CrudRepository<Post, Long> {


}
