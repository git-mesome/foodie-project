package io.wisoft.foodie.project.web.dto;

import io.wisoft.foodie.project.domain.account.Account;
import io.wisoft.foodie.project.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRegisterRequestDto {

    private Long authorId;
    private String title;
    private String content;

    //Test에서 사용
    @Builder
    public PostRegisterRequestDto(Long authorId, String title, String content) {
        this.authorId = authorId;
        this.title = title;
        this.content = content;
    }

    public Post toEntity(Account author){
        return Post.builder()
                .author(author)
                .title(title)
                .content(content)
                .build();
    }

}
