package io.wisoft.foodie.project.domain.chat.web.dto.res;

import io.wisoft.foodie.project.domain.post.persistance.DealStatus;

public record FindAllChatRoomsResponse(Long chatRoomId,
                                       Long postId,
                                       String authorNickname,
                                       String authorProfileImagePath,
                                       String senderNickname,
                                       String senderProfileImagePath,
                                       String postTitle,
                                       String postImagePath,
                                       DealStatus dealStatus
                                       ) {
}
