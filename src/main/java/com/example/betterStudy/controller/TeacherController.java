package com.example.betterStudy.controller;


import com.example.betterStudy.model.Teacher;
import com.example.betterStudy.model.dto.CreateTeacherRequestDTO;
import com.example.betterStudy.model.dto.TeacherResponseDTO;
import com.example.betterStudy.model.dto.UpdateTeacherRequestDTO;
import com.example.betterStudy.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teacher")
public class TeacherController {
    private final TeacherService teacherService;
    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<TeacherResponseDTO> findById(@PathVariable(name = "id")long id){
        TeacherResponseDTO responseDTO = teacherService.findById(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/find-all/")
    public ResponseEntity<Page<TeacherResponseDTO>> findAll(@PageableDefault Pageable pageable, @SortDefault Sort sort){
        Page<TeacherResponseDTO> all = teacherService.findAll(pageable, sort);
        return new ResponseEntity<>(all, HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<TeacherResponseDTO> save(@RequestBody CreateTeacherRequestDTO requestDTO){
        TeacherResponseDTO responseDTO = teacherService.save(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update-teacher")
    public ResponseEntity<TeacherResponseDTO> updateTeacher(@RequestBody UpdateTeacherRequestDTO teacherRequestDTO, @PathVariable(name = "id")long id){
        TeacherResponseDTO responseDTO = teacherService.updateTeacher(teacherRequestDTO, id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
