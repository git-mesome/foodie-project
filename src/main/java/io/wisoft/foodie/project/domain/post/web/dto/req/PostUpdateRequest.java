package io.wisoft.foodie.project.domain.post.web.dto.req;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostUpdateRequest {

    private String title;
    private String content;

    @Builder
    public PostUpdateRequest(String title, String content) {

        this.title = title;
        this.content = content;

    }



}
