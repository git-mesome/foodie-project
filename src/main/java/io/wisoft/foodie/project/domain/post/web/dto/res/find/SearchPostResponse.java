package io.wisoft.foodie.project.domain.post.web.dto.res.find;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record SearchPostResponse(Long postId,
                                 String authorNickname,
                                 String title,
                                 Integer hit,
                                 Boolean likesState,
                                 Integer likesCount,
                                 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
                                 LocalDateTime updateDate) {
}
