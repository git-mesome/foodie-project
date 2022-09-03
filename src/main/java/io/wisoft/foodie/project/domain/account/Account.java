package io.wisoft.foodie.project.domain.account;

import io.wisoft.foodie.project.domain.Grade;
import io.wisoft.foodie.project.domain.post.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "ACCOUNT")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @JsonIgnore
    private String password;

    @Column(name = "nickname")
    private String nickname;

    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grade_id")
    private Grade grade;

    @OneToMany(mappedBy = "author")
    private List<Post> post = new ArrayList<Post>();


    public Account(Long id, String nickname, String email, String password) {

        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.password = password;

    }

    public Account toDomain() {
        return new Account(
                this.id,
                this.nickname,
                this.email,
                this.password
        );
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
