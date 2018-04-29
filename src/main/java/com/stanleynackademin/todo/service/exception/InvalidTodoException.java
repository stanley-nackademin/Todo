package com.stanleynackademin.todo.service.exception;

public final class InvalidTodoException extends RuntimeException {

    public InvalidTodoException(String message) {
        super(message);
    }
}
