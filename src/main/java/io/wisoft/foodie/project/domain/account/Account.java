package io.wisoft.foodie.project.domain.account;

import lombok.Getter;

@Getter
public class Account {

    private final Long id;

    private final String email;

    private final String password;

    private final String nickName;

    public Account(final String email,
                   final String password,
                   final String nickName
    ) {
        this(null, email, password, nickName);
    }

    public Account(final Long id,
                   final String email,
                   final String password,
                   final String nickName
    ) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickName = nickName;
    }

    public void checkPassword(final String password) {
        if (!this.password.equals(password)) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }

    }

}
