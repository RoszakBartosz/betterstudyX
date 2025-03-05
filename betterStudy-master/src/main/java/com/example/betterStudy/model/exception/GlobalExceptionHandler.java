package com.example.betterStudy.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
    @ExceptionHandler({InvalidEmailException.class})
    public ResponseEntity<Object> handleInvalidEmailException(InvalidEmailException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
    @ExceptionHandler({NotFoundStudentException.class})
    public ResponseEntity<Object> handleNotFoundStudentException(NotFoundStudentException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
    @ExceptionHandler({NotFoundClassroomException.class})
    public ResponseEntity<Object> handleNotFoundClassroomException(NotFoundClassroomException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
    @ExceptionHandler({NotFoundLessonException.class})
    public ResponseEntity<Object> handeNotFoundLessonException(NotFoundLessonException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
    @ExceptionHandler({NotFoundUserException.class})
    public ResponseEntity<Object> handeNotFoundUserException(NotFoundUserException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }    @ExceptionHandler({NotFoundTeacherException.class})
    public ResponseEntity<Object> handeNotFoundTeacherException(NotFoundTeacherException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
}
