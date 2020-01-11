package com.honeacademy.webclientexample.service;

import org.springframework.stereotype.Service;

import com.honeacademy.webclientexample.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
	private final PostRepository postRepository;

}
