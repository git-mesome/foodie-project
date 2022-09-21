package io.wisoft.foodie.project.domain.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class PostUploadResponse {

    @JsonProperty("post_image_path")
    private String postImagePath;

    public PostUploadResponse(final String postImagePath) {
        this.postImagePath = postImagePath;
    }

}
