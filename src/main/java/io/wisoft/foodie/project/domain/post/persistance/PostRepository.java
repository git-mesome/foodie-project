package io.wisoft.foodie.project.domain.post.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor {

    List<Post> findByPostTypeOrderByCreateDateDesc(PostType postType);

    @Transactional
    @Modifying
    @Query("update Post p set p.hit = p.hit + 1 where p.id = :id")
    Integer updateHit(Long id);

}
