package io.wisoft.foodie.project.domain.chat.web.dto.res;

public record FindAllChatRoomsResponse(Long chatRoomId,
                                       Long postId,
                                       String authorNickname,
                                       String authorProfileImagePath) {
}
