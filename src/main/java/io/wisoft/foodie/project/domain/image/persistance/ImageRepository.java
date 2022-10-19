package io.wisoft.foodie.project.domain.image.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<PostImage, Long> {

    @Modifying
    @Query("delete from PostImage p where p.post.id = :postId and p.postImagePath like '%:imageName'")
    PostImage deletePostImageName(@Param("postId") Long postId, @Param("imageName") String imageName);

    void deleteByPostIdAndPostImagePathEndsWith(Long postId, String imageNAme);

}
