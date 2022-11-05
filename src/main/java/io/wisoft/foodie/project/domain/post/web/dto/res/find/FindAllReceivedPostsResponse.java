package io.wisoft.foodie.project.domain.post.web.dto.res.find;

import io.wisoft.foodie.project.domain.post.persistance.DealStatus;

public record FindAllReceivedPostsResponse(Long postsId,
                                           String title,
                                           String authorNickname,
                                           DealStatus dealStatus) {
}