package io.wisoft.foodie.project.exception;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(final String message) {
        super(message);
    }

}
