package io.wisoft.foodie.project.domain.post.web.dto.res;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record FindAllPostsResponse(Long postId,
                                   String authorNickname,
                                   String title,
                                   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
                                   LocalDateTime updateDate) {}
