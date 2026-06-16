package com.Faiz.tasks.exceptions;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String email) {
        super("User already exists "+email);
    }
}
