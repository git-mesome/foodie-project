package io.wisoft.foodie.project.domain.image.persistance;

import io.wisoft.foodie.project.domain.post.Post;
import io.wisoft.foodie.project.domain.post.persistance.PostEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "post_image")
@Entity
public class PostImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PostEntity post;

    @Column(name = "post_image_path")
    private String postImagePath;

    public PostImage(final PostEntity post, final String postImagePath) {
        this.post = post;
        this.postImagePath = postImagePath;
    }

}
