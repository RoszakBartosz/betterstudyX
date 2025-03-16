package com.example.betterStudy.model.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){ //TODO unikmakmy zwracania Object, mozna np String
        String message = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidEmailException.class})
    public ResponseEntity<String> handleInvalidEmailException(InvalidEmailException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
    @ExceptionHandler({NotFoundStudentException.class})
    public ResponseEntity<String> handleNotFoundStudentException(NotFoundStudentException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
    @ExceptionHandler({NotFoundClassroomException.class})
    public ResponseEntity<String> handleNotFoundClassroomException(NotFoundClassroomException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
    @ExceptionHandler({NotFoundLessonException.class})
    public ResponseEntity<String> handeNotFoundLessonException(NotFoundLessonException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
    @ExceptionHandler({NotFoundUserException.class})
    public ResponseEntity<String> handeNotFoundUserException(NotFoundUserException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }    @ExceptionHandler({NotFoundTeacherException.class})
    public ResponseEntity<String> handeNotFoundTeacherException(NotFoundTeacherException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
}
