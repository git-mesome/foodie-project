package io.wisoft.foodie.project.domain.post.web.dto.req;

import io.wisoft.foodie.project.domain.post.persistance.PostType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record RegisterPostRequest(@NotBlank(message = "제목은 필수 항목입니다.")
                                  @Size(max = 500, message = "제목은 499자를 초과할 수 없습니다.") String title,
                                  @NotBlank(message = "내용을 작성해주세요.") String content,
                                  @NotBlank(message = "카테고리를 선택해주세요.") String category,
                                  String expirationDate,
                                  String postType) {

    public PostType getPostType() {
        return PostType.valueOf(this.postType);
    }
}
