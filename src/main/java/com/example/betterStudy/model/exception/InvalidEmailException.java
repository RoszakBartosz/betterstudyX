package com.example.betterStudy.model.exception;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String message){ super(message);
    }
}
