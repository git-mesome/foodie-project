package io.wisoft.foodie.project.domain.post.web.dto.res.find;

public record FindAllLikesResponse(Long postId,
                                   String title,
                                   String authorNickname,
                                   Integer likesCounts) {
}
