package io.wisoft.foodie.project.service;

import io.wisoft.foodie.project.domain.post.Post;
import io.wisoft.foodie.project.domain.post.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostService postService;

    @Test
    public void save() throws Exception{

        //given
        Post post = new Post();

        postRepository.save(Post.builder()
                        .title("test")
                        .content("minseo")
                .build());

    }

}