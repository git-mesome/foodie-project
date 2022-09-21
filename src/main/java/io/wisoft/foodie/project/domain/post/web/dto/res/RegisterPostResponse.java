package io.wisoft.foodie.project.domain.post.web.dto.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.wisoft.foodie.project.domain.post.Post;
import io.wisoft.foodie.project.domain.post.persistance.PostEntity;
import lombok.Getter;

import java.util.List;

@Getter
public class RegisterPostResponse {

    @JsonProperty("author_id")
    private String authorId;
    private String title;
    private String content;

    @JsonProperty("post_image_path")
    private String postImagePath;


    public RegisterPostResponse(final PostEntity post, final String postImagePath) {
        this.authorId = post.getAuthor().getNickName();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.postImagePath = postImagePath;
    }

}
