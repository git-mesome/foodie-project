package io.wisoft.foodie.project.domain.post.web.dto.res.find;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.wisoft.foodie.project.domain.post.persistance.DealStatus;

import java.time.LocalDateTime;

public record FindAllPostsResponse(Long postId,
                                   String authorProfileImagePath,
                                   String authorNickname,
                                   String title,
                                   String gu,
                                   String dong,
                                   Integer hit,
                                   Boolean likesState,
                                   Integer likesCount,
                                   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
                                   LocalDateTime updateDate,
                                   String postImagePath,
                                   DealStatus dealStatus) {
}
