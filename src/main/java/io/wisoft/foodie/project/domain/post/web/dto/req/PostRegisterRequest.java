package io.wisoft.foodie.project.domain.post.web.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.wisoft.foodie.project.domain.account.Account;
import io.wisoft.foodie.project.domain.post.Post;
import io.wisoft.foodie.project.domain.post.persistance.PostEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostRegisterRequest {

    @NotBlank(message = "제목은 필수 항목입니다.")
    @Size(max = 500, message = "제목은 499자를 초과할 수 없습니다.")
    private String title;

    @NotBlank(message = "내용을 작성해주세요.")
    private String content;

    @JsonProperty("post_image_path")
    private String postImagePath;

    public PostEntity toEntity(){
        return PostEntity.builder()
                .title(title)
                .content(content)
                .build();
    }

}
