package com.honeacademy.webclientexample.controller;

import com.honeacademy.webclientexample.dto.PostDto;
import com.honeacademy.webclientexample.model.Post;
import com.honeacademy.webclientexample.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {


    private final PostService postService;

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<Post> createPost(@RequestBody @Valid final PostDto postDto) {
        return postService.savePost(postDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<Post> updatePost(@RequestBody final PostDto request, @PathVariable Long id) throws Exception {
        return postService.updatePost(request, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<HttpStatus> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return Mono.just(HttpStatus.NO_CONTENT);
    }

    @GetMapping("")
    @ResponseStatus(code = HttpStatus.OK)
    public Mono<List<Post>> listPosts(@RequestParam(name = "offset", defaultValue = "0") @Min(0) final int offset,
                                      @RequestParam(name = "limit", defaultValue = "20") @Min(1) final int limit) {
        return postService.listPosts(offset, limit);
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Mono<Post> getPost(@PathVariable Long id) throws Exception {
        return postService.getPostById(id);
    }

}
