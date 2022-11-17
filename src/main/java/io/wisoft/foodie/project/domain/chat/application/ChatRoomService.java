package io.wisoft.foodie.project.domain.chat.application;

import io.wisoft.foodie.project.domain.account.persistance.Account;
import io.wisoft.foodie.project.domain.account.persistance.AccountRepository;
import io.wisoft.foodie.project.domain.chat.persistance.ChatMessage;
import io.wisoft.foodie.project.domain.chat.persistance.ChatMessageRepository;
import io.wisoft.foodie.project.domain.chat.persistance.ChatRoom;
import io.wisoft.foodie.project.domain.chat.persistance.ChatRoomRepository;
import io.wisoft.foodie.project.domain.chat.web.dto.res.FindAllChatRoomsResponse;
import io.wisoft.foodie.project.domain.chat.web.dto.res.FindChatMessageDetailResponse;
import io.wisoft.foodie.project.domain.chat.web.dto.res.FindChatRoomResponse;
import io.wisoft.foodie.project.domain.post.persistance.Post;
import io.wisoft.foodie.project.domain.post.persistance.PostRepository;
import io.wisoft.foodie.project.exception.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final PostRepository postRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public ChatRoomService(final ChatRoomRepository chatRoomRepository,
                           final ChatMessageRepository chatMessageRepository,
                           final PostRepository postRepository,
                           final AccountRepository accountRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.chatMessageRepository = chatMessageRepository;
        this.postRepository = postRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public FindChatRoomResponse createOrFind(final Long postId,
                                             final Long senderId) {

        final Account account = this.accountRepository.findById(senderId)
            .orElseThrow(() -> new AccountNotFoundException("존재하지 않는 회원정보입니다."));
        final Post post = this.postRepository.findById(postId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));


        Optional<ChatRoom> optionalChatRoom;
        ChatRoom chatRoom;
        optionalChatRoom = this.chatRoomRepository.findByPostIdAndSenderId(post.getId(), post.getAuthor().getId(), account.getId());
        if (optionalChatRoom.isEmpty()) {
            chatRoom = this.chatRoomRepository.save(
                new ChatRoom(
                    post,
                    post.getAuthor(),
                    account
                ));
        } else {
            chatRoom = optionalChatRoom.get();
        }

        return new FindChatRoomResponse(chatRoom.getId());

    }

    @Transactional
    public List<FindAllChatRoomsResponse> findAll(final Long accountId) {

        List<ChatRoom> chatRoomList = this.chatRoomRepository.findAllChatRoomByAccountId(accountId);

        return chatRoomList.stream()
            .map(chatRoom -> new FindAllChatRoomsResponse(
                chatRoom.getId(),
                chatRoom.getPost().getId(),
                chatRoom.getAuthor().getNickname(),
                chatRoom.getAuthor().getProfileImagePath()
            )).toList();
    }

    @Transactional
    public List<FindChatMessageDetailResponse> findAllChatMessageByRoomId(final Long id,
                                                                          final Long accountId) {
        final ChatRoom chatRoom = this.chatRoomRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채팅방입니다."));
        final Account account = this.accountRepository.findById(accountId)
            .orElseThrow(() -> new AccountNotFoundException("존재하지 않는 회원정보입니다."));

        List<ChatMessage> chatMessageList = chatMessageRepository.findByChatRoomId(chatRoom.getId());

        return chatMessageList.stream()
            .map(chatMessage -> new FindChatMessageDetailResponse(
                chatMessage.getId(),
                chatMessage.getSender().getNickname(),
                chatMessage.getMessage(),
                chatMessage.getCreateDate()
            )).toList();
    }

}
