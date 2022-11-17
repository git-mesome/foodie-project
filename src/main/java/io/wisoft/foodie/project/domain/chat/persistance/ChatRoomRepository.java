package io.wisoft.foodie.project.domain.chat.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    @Query("""
            select c 
            from ChatRoom c 
            where c.author.id = :accountId 
            or  c.sender.id = :accountId
            """)
    List<ChatRoom> findAllChatRoomByAccountId(final Long accountId);

    @Query("""
            select c
            from ChatRoom c
            where c.post.id = :postId
            and c.author.id = :authorId
            and c.sender.id = :senderId
            """)
    Optional<ChatRoom> findByPostIdAndSenderId(final Long postId,final Long authorId, final Long senderId);

}
