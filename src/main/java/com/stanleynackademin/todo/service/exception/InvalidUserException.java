package com.stanleynackademin.todo.service.exception;

public final class InvalidUserException extends RuntimeException {

    public InvalidUserException(String message) {
        super(message);
    }
}
