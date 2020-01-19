package com.honeacademy.webclientexample.service;

import com.honeacademy.webclientexample.model.Post;
import com.honeacademy.webclientexample.repository.PostRepository;
import com.honeacademy.webclientexample.utils.TestUtils;
import javassist.NotFoundException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.honeacademy.webclientexample.utils.TestUtils.mockPost;
import static com.honeacademy.webclientexample.utils.TestUtils.mockPostDto;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class PostServiceTest {
    @Mock
    PostRepository postRepository;
    PostService postService;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        postService= new PostService(postRepository);
    }

    @Test
    public void save_post_successful_returns_post(){
        Post mockPost=mockPost();
        given(postRepository.save(any(Post.class))).willReturn(mockPost);

        Mono<Post> post = postService.savePost(mockPostDto());

        assertNotNull(post);
        StepVerifier.create(post)
                .thenConsumeWhile(
                        result -> {
                            assertNotNull(result);
                            Assert.isTrue(mockPost.getId().equals(result.getId()),"id not equal");
                            Assert.isTrue(mockPost.getBody().equals(result.getBody()),"body not equal");
                            Assert.isTrue(mockPost.getTitle().equals(result.getTitle()), "title not equal");
                            return true;
                        })
                .verifyComplete();

        verify(postRepository, times(1)).save(any(Post.class));
    }
    @Test
    public void update_post_successful_returns_post() throws Exception {
        Post mockPost=mockPost();
        Optional<Post> optionalMockPost= Optional.of(mockPost);

        given(postRepository.save(any(Post.class))).willReturn(mockPost);
        given(postRepository.findById(any(Long.class))).willReturn(optionalMockPost);

        Mono<Post> post = postService.updatePost(mockPostDto(),2l);

        assertNotNull(post);
        StepVerifier.create(post)
                .thenConsumeWhile(
                        result -> {
                            assertNotNull(result);
                            Assert.isTrue(mockPost.getId().equals(result.getId()),"id not equal");
                            Assert.isTrue(mockPost.getBody().equals(result.getBody()),"body not equal");
                            Assert.isTrue(mockPost.getTitle().equals(result.getTitle()), "title not equal");
                            return true;
                        })
                .verifyComplete();

        verify(postRepository, times(1)).findById(any(Long.class));
        verify(postRepository, times(1)).save(any(Post.class));
    }

    @Test
    public void update_post_throws_exception_when_existing_post_not_found() throws Exception {
        expectedException.expect(NotFoundException.class);

        given(postRepository.findById(any(Long.class))).willReturn(Optional.empty());

        postService.updatePost(mockPostDto(),2l);

        verify(postRepository, never()).save(mockPost());
    }

    @Test
    public void get_post_by_id_post_successful_returns_post() throws Exception {
        Post mockPost=mockPost();

        Optional<Post> optionalMockPost= Optional.of(mockPost);

        given(postRepository.findById(any(Long.class))).willReturn(optionalMockPost);

        Mono<Post> post = postService.getPostById(2l);

        assertNotNull(post);
        StepVerifier.create(post)
                .thenConsumeWhile(
                        result -> {
                            assertNotNull(result);
                            Assert.isTrue(mockPost.getId().equals(result.getId()),"id not equal");
                            Assert.isTrue(mockPost.getBody().equals(result.getBody()),"body not equal");
                            Assert.isTrue(mockPost.getTitle().equals(result.getTitle()), "title not equal");
                            return true;
                        })
                .verifyComplete();

        verify(postRepository, times(1)).findById(any(Long.class));
    }

    @Test
    public void get_post_by_id_throws_exception_when_existing_post_not_found() throws Exception {
        expectedException.expect(NotFoundException.class);

        given(postRepository.findById(any(Long.class))).willReturn(Optional.empty());

        postService.getPostById(2l);

        verify(postRepository, times(1)).findById(any(Long.class));
    }
    @Test
    public void list_post_successful_returns_posts_list() throws Exception {
        Post mockPost=mockPost();
        List<Post> posts = new ArrayList<>();
        posts.add(mockPost);

        Page<Post> pagedResponse = mock(Page.class);

        given(pagedResponse.getTotalElements()).willReturn(1l);
        given(pagedResponse.getTotalPages()).willReturn(1);
        given(pagedResponse.getContent()).willReturn(posts);




        given(postRepository.findAll(any(Pageable.class))).willReturn(pagedResponse);

        Mono<List<Post>> post = postService.listPosts(0,20);

        assertNotNull(post);
        StepVerifier.create(post)
                .thenConsumeWhile(
                        result -> {
                            assertNotNull(result);
                            Assert.isTrue(mockPost.getId().equals(result.get(0).getId()),"id not equal");
                            Assert.isTrue(mockPost.getBody().equals(result.get(0).getBody()),"body not equal");
                            Assert.isTrue(mockPost.getTitle().equals(result.get(0).getTitle()), "title not equal");
                            return true;
                        })
                .verifyComplete();

        verify(postRepository, times(1)).findAll(any(Pageable.class));
    }


}
