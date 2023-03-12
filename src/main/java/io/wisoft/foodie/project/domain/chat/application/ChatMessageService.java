package io.wisoft.foodie.project.domain.chat.application;

import io.wisoft.foodie.project.domain.account.persistance.Account;
import io.wisoft.foodie.project.domain.account.persistance.AccountRepository;
import io.wisoft.foodie.project.domain.auth.exception.ChatRoomException;
import io.wisoft.foodie.project.domain.auth.web.ErrorCode;
import io.wisoft.foodie.project.domain.chat.persistance.ChatMessage;
import io.wisoft.foodie.project.domain.chat.persistance.ChatMessageRepository;
import io.wisoft.foodie.project.domain.chat.persistance.ChatRoom;
import io.wisoft.foodie.project.domain.chat.persistance.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public ChatMessageService(final ChatMessageRepository chatMessageRepository,
                              final ChatRoomRepository chatRoomRepository,
                              final AccountRepository accountRepository) {
        this.chatMessageRepository = chatMessageRepository;
        this.chatRoomRepository = chatRoomRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public ChatMessage create(final Long chatRoomId,
                              final Long senderId,
                              final String message){
        final ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(()-> new ChatRoomException(ErrorCode.NOT_FOUND_CHAT_ROOM));
        final Account sender = accountRepository.findById(senderId).orElseThrow();
        return chatMessageRepository.save(
                ChatMessage.createChat(chatRoom,sender,message));
    }


}
