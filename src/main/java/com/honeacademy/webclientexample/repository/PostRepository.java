package com.honeacademy.webclientexample.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.honeacademy.webclientexample.model.Post;

public interface PostRepository extends PagingAndSortingRepository<Post, Long> {


}
