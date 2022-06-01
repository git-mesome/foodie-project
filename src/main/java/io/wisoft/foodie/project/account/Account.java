package io.wisoft.foodie.project.account;

import lombok.Getter;

@Getter
public class Account {

    private Long id;
    private String name;
    private String email;
    private String password;

    public Account(final String name, final String email, final String password) {
        this(null, name, email, password);
    }

    public Account(final Long id, final String name, final String email, final String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}

