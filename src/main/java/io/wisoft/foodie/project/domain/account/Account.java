package io.wisoft.foodie.project.domain.account;

import io.wisoft.foodie.project.domain.Grade;
import io.wisoft.foodie.project.domain.post.Post;
import io.wisoft.foodie.project.web.dto.AccountDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.test.context.support.AnnotationConfigContextLoaderUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {

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
    private final List<Post> post = new ArrayList<Post>();


    public Account(final String email, final String password, final String nickName) {

        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.grade = Grade.builder()
                .id(1L)
                .name("짱").build();

    }

    public static Account from(final AccountDto accountDto){
        return new Account(
                accountDto.getEmail(),
                accountDto.getPassword(),
                accountDto.getNickName()
        );
    }

    public AccountDto toDomain() {
        return new AccountDto(
                this.id,
                this.email,
                this.password,
                this.nickName
        );
    }

}
