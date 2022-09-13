package io.wisoft.foodie.project.service;

import io.wisoft.foodie.project.domain.post.application.PostService;
import io.wisoft.foodie.project.domain.post.persistance.PostEntity;
import io.wisoft.foodie.project.domain.post.persistance.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class PostEntityServiceTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostService postService;

    @Test
    public void save() throws Exception{

        //given
        PostEntity postEntity = new PostEntity();

        postRepository.save(PostEntity.builder()
                        .title("test")
                        .content("minseo")
                .build());

    }

}