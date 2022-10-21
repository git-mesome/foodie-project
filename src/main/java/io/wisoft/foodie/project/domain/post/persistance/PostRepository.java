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

    @Query("select p from Post p where p.author.id = :authorId and p.postType not in (:recipe) order by p.createDate desc ")
    List<Post> findAllSharedPostByAuthorId(@Param("authorId") Long authorId, @Param("recipe") PostType recipe);

    @Query("select p from Post p where p.taker.id = :accountId and p.postType not in (:recipe) and p.dealStatus = :finish order by p.createDate desc")
    List<Post> findAllReceivedPostByTakerId(@Param("accountId") Long accountId,
                                            @Param("recipe") PostType recipe,
                                            @Param("finish") DealStatus finish);


    @Query("SELECT p FROM Account a, Post p, Likes l WHERE l.post.id = p.id AND l.account.id = a.id AND l.account.id = :accountId")
    List<Post> findAllPostByLikesAccountId(Long accountId);

    @Modifying
    @Query("update Post p set p.likesCount = p.likesCount + :plusOrMinus where p.id =:id")
    Integer updateLikesCount(@Param("id") Long id, @Param("plusOrMinus") Integer plusOrMinus);

}
