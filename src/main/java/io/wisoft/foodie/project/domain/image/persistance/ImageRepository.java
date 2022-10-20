package io.wisoft.foodie.project.domain.image.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<PostImage, Long> {

    void deleteByPostIdAndPostImagePathEndsWith(Long postId, String imageName);

}
