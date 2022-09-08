package io.wisoft.foodie.project.web.dto.res;

import io.wisoft.foodie.project.domain.post.Post;
import lombok.Getter;

@Getter
public class PostResponseDto {

    private Long postId;
    private String title;
    private String content;
    private Long authorId;

    public PostResponseDto(Post entity) {
        this.postId = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.authorId = entity.getAuthor().getId();
    }


}
