package io.wisoft.foodie.project.domain.post.persistance;

import io.wisoft.foodie.project.domain.account.persistance.AccountEntity;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "grade")
    private List<AccountEntity> accountEntities = new ArrayList<>();


    @Builder
    public Grade(Long id, String name, List<AccountEntity> accountEntities) {
        this.id = id;
        this.name = name;
        this.accountEntities = accountEntities;
    }
}
