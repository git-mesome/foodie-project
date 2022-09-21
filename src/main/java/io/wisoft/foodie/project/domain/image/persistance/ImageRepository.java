package io.wisoft.foodie.project.domain.image.persistance;

import io.wisoft.foodie.project.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<PostImage,Long> {
    List<PostImage> findAllByPost(Post postId);
}
