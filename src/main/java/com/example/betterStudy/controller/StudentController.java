package com.example.betterStudy.controller;


import com.example.betterStudy.model.dto.CreateStudentRequestDTO;
import com.example.betterStudy.model.dto.StudentResponseDTO;
import com.example.betterStudy.model.dto.UpdateStudentRequestDTO;
import com.example.betterStudy.service.StudentService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/find-all")
    public ResponseEntity<Page<StudentResponseDTO>> findAll(@Valid @PageableDefault Pageable pageable){
        return new ResponseEntity<>(studentService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<StudentResponseDTO> findById(@PathVariable(name = "id")Long id){
        return new ResponseEntity<>(studentService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<StudentResponseDTO> save(@Valid @RequestBody CreateStudentRequestDTO requestDTO){
        return new ResponseEntity<>(studentService.save(requestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update-student/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@Valid @RequestBody UpdateStudentRequestDTO requestDTO, @PathVariable(name = "id")long id){
        return new ResponseEntity<>(studentService.updateStudent(requestDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus delete(@Valid @PathVariable(name = "id")long id){
        studentService.delete(id);
        return HttpStatus.OK;
    }
}
