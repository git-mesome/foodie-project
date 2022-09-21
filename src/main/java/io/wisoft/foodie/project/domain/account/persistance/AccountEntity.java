package io.wisoft.foodie.project.domain.account.persistance;

import io.wisoft.foodie.project.domain.BaseTimeEntity;
import io.wisoft.foodie.project.domain.post.persistance.Grade;
import io.wisoft.foodie.project.domain.account.Account;
import io.wisoft.foodie.project.domain.post.persistance.PostEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "account")
public class AccountEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;
    @JsonIgnore
    private String password;

    @Column(name="nickname")
    private String nickName;

    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grade_id")
    private Grade grade;

    @OneToMany(mappedBy = "author")
    private final List<PostEntity> postEntity;
    {
        postEntity = new ArrayList<PostEntity>();
    }


    public AccountEntity(final String email, final String password, final String nickName) {

        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.grade = Grade.builder()
                .id(1L)
                .name("짱").build();

    }

    public static AccountEntity from(final Account account){
        return new AccountEntity(
                account.getEmail(),
                account.getPassword(),
                account.getNickName()
        );
    }

    public Account toDomain() {
        return new Account(
                this.id,
                this.email,
                this.password,
                this.nickName
        );
    }

}
