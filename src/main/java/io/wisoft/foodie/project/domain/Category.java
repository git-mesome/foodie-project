package io.wisoft.foodie.project.domain;

import io.wisoft.foodie.project.domain.post.Post;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="name")
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Post> posts = new ArrayList<Post>();

}
