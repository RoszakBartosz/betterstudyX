package com.example.betterStudy.model.exception;

public class NotFoundStudentException extends RuntimeException{
    public NotFoundStudentException(){super();}
    public NotFoundStudentException(String s) {
        System.err.println(s);
    }
}
