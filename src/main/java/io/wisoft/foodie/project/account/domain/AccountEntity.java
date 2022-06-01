package io.wisoft.foodie.project.account.domain;

import io.wisoft.foodie.project.account.Account;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "ACCOUNT")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public AccountEntity() {

    }

    @Builder
    public AccountEntity(Long id, String name, String email, String password) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;

    }

    public Account toDomain() {
        return new Account(
                this.id,
                this.name,
                this.email,
                this.password
        );
    }

}
