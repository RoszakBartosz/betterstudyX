package com.example.betterStudy.model.exception;

public class NotFoundLessonException extends RuntimeException{
    public NotFoundLessonException() {
        super("Lesson not found");
    }
}
