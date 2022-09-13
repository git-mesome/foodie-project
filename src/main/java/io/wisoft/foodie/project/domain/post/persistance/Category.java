package io.wisoft.foodie.project.domain.post.persistance;

import io.wisoft.foodie.project.domain.post.persistance.PostEntity;

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
    private List<PostEntity> postEntities = new ArrayList<PostEntity>();

}
