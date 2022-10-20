package io.wisoft.foodie.project.domain.post.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor {

    List<Post> findByPostTypeOrderByCreateDateDesc(PostType postType);

    @Modifying
    @Query("update Post p set p.likesCount = p.likesCount + :plusOrMinus where p.id =:id")
    Integer updateLikesCount(@Param("id") Long id, @Param("plusOrMinus") Integer plusOrMinus);

}
