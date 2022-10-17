package io.wisoft.foodie.project.domain.post.persistance.category;

import io.wisoft.foodie.project.domain.post.persistance.Post;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Post> postList = new ArrayList<Post>();

    public void mappingPost(Post post) {
        this.postList.add(post);
    }

}
