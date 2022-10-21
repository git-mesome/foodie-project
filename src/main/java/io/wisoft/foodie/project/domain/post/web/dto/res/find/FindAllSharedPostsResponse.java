package io.wisoft.foodie.project.domain.post.web.dto.res.find;

import io.wisoft.foodie.project.domain.post.persistance.DealStatus;

public record FindAllSharedPostsResponse(Long postId,
                                         String title,
                                         String takerNickname,
                                         DealStatus dealStatus) {
}
