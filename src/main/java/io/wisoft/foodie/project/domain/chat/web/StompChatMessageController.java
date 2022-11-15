package io.wisoft.foodie.project.domain.chat.web;

import io.wisoft.foodie.project.domain.chat.application.ChatMessageService;
import io.wisoft.foodie.project.domain.chat.persistance.ChatMessage;
import io.wisoft.foodie.project.domain.chat.web.dto.req.FindChatMessageDetailRequest;
import io.wisoft.foodie.project.domain.chat.web.dto.res.FindChatMessageDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class StompChatMessageController {

    private final ChatMessageService chatMessageService;

    @Autowired
    public StompChatMessageController(final ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    @MessageMapping("/chat/{chat-room-id}")
    @SendTo("/sub/{chat-room-id}")
    public FindChatMessageDetailResponse send(
        @DestinationVariable("chat-room-id") final Long chatRoomId,
        final FindChatMessageDetailRequest request
    ) {
        final ChatMessage message = chatMessageService
            .create(chatRoomId, request.senderId(), request.message());

        return new FindChatMessageDetailResponse(
            chatRoomId,
            message.getSender().getNickname(),
            message.getMessage(),
            message.getCreateDate()
        );
    }

}
