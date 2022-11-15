package io.wisoft.foodie.project.domain.post.web.dto.res.find;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.wisoft.foodie.project.domain.post.persistance.DealStatus;
import io.wisoft.foodie.project.domain.post.persistance.Grade;
import io.wisoft.foodie.project.domain.post.persistance.PostType;

import java.time.LocalDateTime;
import java.util.List;

public record FindPostDetailResponse(Long postId,
                                     String authorNickname,
                                     String authorProfilePath,
                                     Grade authorGrade,
                                     String category,
                                     String title,
                                     String content,
                                     Integer hit,
                                     Boolean likesState,
                                     Integer likesCount,
                                     String siDo,
                                     String siGunGu,
                                     String eupMyeonDong,
                                     PostType postType,
                                     DealStatus dealStatus,
                                     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
                                     LocalDateTime createDate,
                                     List<String> postImagePath) {
}
