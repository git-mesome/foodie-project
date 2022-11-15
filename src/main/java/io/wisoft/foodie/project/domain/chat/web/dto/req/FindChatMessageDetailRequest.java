package io.wisoft.foodie.project.domain.chat.web.dto.req;

public record FindChatMessageDetailRequest(Long chatRoomId,
                                           Long senderId,
                                           String message) {
}
