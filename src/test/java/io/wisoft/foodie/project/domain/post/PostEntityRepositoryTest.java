package io.wisoft.foodie.project.domain.post;

import io.wisoft.foodie.project.domain.account.persistance.AccountEntity;
import io.wisoft.foodie.project.domain.post.persistance.PostEntity;
import io.wisoft.foodie.project.domain.post.persistance.PostRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostEntityRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @AfterEach
    public void cleanup() {
        postRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {

        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";
        AccountEntity author = new AccountEntity();

        postRepository.save(io.wisoft.foodie.project.domain.post.persistance.PostEntity.builder() // 2
                .title(title)
                .content(content)
                .author(author)
                .build());
        //when
        List<PostEntity> postsList = postRepository.findAll(); // 3

        //then
        PostEntity posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록 () {

        AccountEntity author = new AccountEntity();

        //given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        postRepository.save(io.wisoft.foodie.project.domain.post.persistance.PostEntity.builder()
                .title("title")
                .content("content")
                .author(author)
                .build());
        //when
        List<PostEntity> postsList = postRepository.findAll();
        //then
        PostEntity posts = postsList.get(0);
        System.out.println(">>>>>>>>> createDate="+posts.getCreateDate()
                +", updateDate="+posts.getUpdateDate());
        assertThat(posts.getCreateDate()).isAfter(Instant.from(now));
        assertThat(posts.getUpdateDate()).isAfter(Instant.from(now));

    }
}