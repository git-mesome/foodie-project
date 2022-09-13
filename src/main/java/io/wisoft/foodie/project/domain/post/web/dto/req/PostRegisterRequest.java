package io.wisoft.foodie.project.domain.post.web.dto.req;

import io.wisoft.foodie.project.domain.account.persistance.AccountEntity;
import io.wisoft.foodie.project.domain.post.persistance.PostEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRegisterRequest {

    private Long authorId;
    private String title;
    private String content;

    //Test에서 사용
    @Builder
    public PostRegisterRequest(Long authorId, String title, String content) {
        this.authorId = authorId;
        this.title = title;
        this.content = content;
    }

    public PostEntity toEntity(AccountEntity author){
        return io.wisoft.foodie.project.domain.post.persistance.PostEntity.builder()
                .author(author)
                .title(title)
                .content(content)
                .build();
    }

}
