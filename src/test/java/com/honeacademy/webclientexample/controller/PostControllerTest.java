package com.honeacademy.webclientexample.controller;

import com.honeacademy.webclientexample.WebClientExampleApplication;
import com.honeacademy.webclientexample.model.Post;
import com.honeacademy.webclientexample.service.PostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.Assert;
import reactor.core.publisher.Mono;

import static com.honeacademy.webclientexample.utils.TestUtils.mockPost;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebFluxTest(PostController.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {WebClientExampleApplication.class})
public class PostControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    private PostService postService;

    @Test
    public void test_get_post_by_id() throws Exception {
        Post post = mockPost();

        Mono<Post> postMono = Mono.just(post);

        when(postService.getPostById(1l)).thenReturn(postMono);

        webTestClient.get()
                .uri("/posts/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Post.class)
                .value(response -> {
                    assertNotNull(response);
                    Assert.isTrue(response.getId().equals(post.getId()),"id not equal");
                    Assert.isTrue(response.getBody().equals(post.getBody()),"body not equal");
                    Assert.isTrue(response.getTitle().equals(post.getTitle()), "title not equal");
                });
    }
}
