package com.example.betterStudy.model.exception;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String thisEmailAlreadyExists) {
    }
}
