package io.wisoft.foodie.project.domain.chat.web;

import io.wisoft.foodie.project.domain.chat.application.ChatRoomService;
import io.wisoft.foodie.project.domain.chat.web.dto.res.FindAllChatRoomsResponse;
import io.wisoft.foodie.project.domain.chat.web.dto.res.FindChatMessageDetailResponse;
import io.wisoft.foodie.project.domain.chat.web.dto.res.FindChatRoomResponse;
import io.wisoft.foodie.project.global.resolver.AccountIdentifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @Autowired
    public ChatRoomController(
        final ChatRoomService chatRoomService
    ) {
        this.chatRoomService = chatRoomService;
    }

    @PostMapping("/{post-id}")
    public ResponseEntity<FindChatRoomResponse> createOrFind(
        @PathVariable("post-id") final Long postId,
        @AccountIdentifier final Long senderId
    ) {
        System.out.println("#####" + senderId);
        System.out.println("#####PPPP" + postId);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(chatRoomService.createOrFind(postId, senderId));
    }

    @GetMapping
    public ResponseEntity<List<FindAllChatRoomsResponse>> findAll(
        @AccountIdentifier final Long accountId
    ) {
        return ResponseEntity
            .ok(chatRoomService.findAll(accountId));
    }


    @GetMapping("/{id}")
    public ResponseEntity<List<FindChatMessageDetailResponse>> enterChatRoom(
        @PathVariable("id") final Long id,
        @AccountIdentifier final Long accountId
    ) {
        return ResponseEntity
            .ok(chatRoomService.findAllChatMessageByRoomId(id, accountId));
    }


}
