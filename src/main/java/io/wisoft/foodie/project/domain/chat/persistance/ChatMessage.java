package io.wisoft.foodie.project.domain.chat.persistance;

import io.wisoft.foodie.project.domain.account.persistance.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
@Entity
@Table(name = "chat_message")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Account sender;

    @ManyToOne
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    private String message;

        @CreatedDate
        private LocalDateTime createDate;

    public ChatMessage(final ChatRoom chatRoom,
                       final Account sender,
                       final String message) {
        this.chatRoom = chatRoom;
        this.sender = sender;
        this.message = message;
    }

    public static ChatMessage createChat(final ChatRoom chatRoom,
                                         final Account sender,
                                         final String message) {
        return new ChatMessage(chatRoom,
                sender,
                message);
    }

}
