package io.wisoft.foodie.project.handler.exception;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(final String message) {
        super(message);
    }

}
