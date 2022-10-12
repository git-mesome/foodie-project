package io.wisoft.foodie.project.domain.post.web.dto.res;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.wisoft.foodie.project.domain.post.persistance.DealStatus;
import io.wisoft.foodie.project.domain.post.persistance.Grade;

import java.time.LocalDateTime;
import java.util.List;

public record FindPostDetailResponse(Long postId,
                                     String authorNickname,
                                     String authorProfilePath,
                                     Grade authorGrade,
                                     String title,
                                     String content,
                                     Integer hit,
                                     String siDo,
                                     String siGunGu,
                                     String eupMyeonDong,
                                     DealStatus dealStatus,
                                     LocalDateTime createDate,
                                     List<String> postImagePath) {

    //Todo Integer postLikes

}
