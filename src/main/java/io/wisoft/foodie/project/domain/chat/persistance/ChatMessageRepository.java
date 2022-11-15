package io.wisoft.foodie.project.domain.chat.persistance;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends CrudRepository<ChatMessage, Long> {

    List<ChatMessage> findByChatRoomId(final Long chatRoomId);

}
