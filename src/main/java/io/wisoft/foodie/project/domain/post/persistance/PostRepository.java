package io.wisoft.foodie.project.domain.post.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long>, JpaSpecificationExecutor {

    List<Post> findByPostTypeOrderByCreateDateDesc(PostType postType);

}
