package com.example.betterStudy.controller;


import com.example.betterStudy.model.dto.CreateStudentRequestDTO;
import com.example.betterStudy.model.dto.StudentResponseDTO;
import com.example.betterStudy.model.dto.UpdateStudentRequestDTO;
import com.example.betterStudy.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/find-all")
    public ResponseEntity<Page<StudentResponseDTO>> findAll(@PageableDefault Pageable pageable){
        return new ResponseEntity<>(studentService.findAll(pageable), HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<StudentResponseDTO> save (@RequestBody CreateStudentRequestDTO requestDTO){
        return new ResponseEntity<>(studentService.save(requestDTO), HttpStatus.CREATED);
    }
    @PutMapping("/update-student/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@RequestBody UpdateStudentRequestDTO requestDTO, @PathVariable(name = "id")long id){
        return new ResponseEntity<>(studentService.updateStudent(requestDTO, id), HttpStatus.OK);
    }
}
