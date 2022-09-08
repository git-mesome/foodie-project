package io.wisoft.foodie.project.domain.post;

import io.wisoft.foodie.project.domain.account.Account;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostRepositoryTest {

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
        Account author = new Account();

        postRepository.save(Post.builder() // 2
                .title(title)
                .content(content)
                .author(author)
                .build());
        //when
        List<Post> postsList = postRepository.findAll(); // 3

        //then
        Post posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록 () {

        Account author = new Account();

        //given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        postRepository.save(Post.builder()
                .title("title")
                .content("content")
                .author(author)
                .build());
        //when
        List<Post> postsList = postRepository.findAll();
        //then
        Post posts = postsList.get(0);
        System.out.println(">>>>>>>>> createDate="+posts.getCreateDate()
                +", updateDate="+posts.getUpdateDate());
        assertThat(posts.getCreateDate()).isAfter(Instant.from(now));
        assertThat(posts.getUpdateDate()).isAfter(Instant.from(now));

    }
}