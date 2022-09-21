package io.wisoft.foodie.project.domain.post.web.dto.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.wisoft.foodie.project.domain.post.persistance.PostEntity;
import lombok.Getter;

@Getter
public class FindByPostIdResponse {

        @JsonProperty("author_id")
        private String authorId;
        private String title;
        private String content;

    public FindByPostIdResponse(PostEntity post) {
        this.authorId = post.getAuthor().getNickName();
        this.title = post.getTitle();
        this.content = post.getContent();
    }

}
