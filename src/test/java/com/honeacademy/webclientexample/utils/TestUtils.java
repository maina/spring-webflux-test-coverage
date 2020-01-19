package com.honeacademy.webclientexample.utils;

import com.honeacademy.webclientexample.dto.PostDto;
import com.honeacademy.webclientexample.model.Post;

public class TestUtils {

    public static Post mockPost() {
        Post post = new Post();
        post.setId(1l);
        post.setBody("sample body");
        post.setTitle("sample title");
        post.setUserId(1l);
        return post;
    }

    public static PostDto mockPostDto() {
        PostDto postDto = new PostDto();
        postDto.setBody("sample body");
        postDto.setTitle("sample title");
        return postDto;

    }
}
