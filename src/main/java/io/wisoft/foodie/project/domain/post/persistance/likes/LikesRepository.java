package io.wisoft.foodie.project.domain.post.persistance.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {

    Optional<Likes> findLikesByAccountIdAndPostId(Long accountId, Long postId);

}
