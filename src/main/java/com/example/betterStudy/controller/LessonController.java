package com.example.betterStudy.controller;

import com.example.betterStudy.model.dto.CreateLessonRequestDTO;
import com.example.betterStudy.model.dto.LessonResponseDTO;
import com.example.betterStudy.model.dto.UpdateLessonRequestDTO;
import com.example.betterStudy.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lesson")
public class LessonController {
    private final LessonService lessonService;
    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<LessonResponseDTO> findById(@PathVariable(name = "id") long id){
        return new ResponseEntity<>(lessonService.findById(id), HttpStatus.OK);
    }
    @GetMapping("/find-all")
    public ResponseEntity<Page<LessonResponseDTO>> findAll(@PageableDefault Pageable pageable){
        return new ResponseEntity<>(lessonService.findAll(pageable), HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<LessonResponseDTO> save(@RequestBody CreateLessonRequestDTO requestDTO){
        return new ResponseEntity<>(lessonService.save(requestDTO), HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<LessonResponseDTO> updateLesson(@RequestBody UpdateLessonRequestDTO requestDTO, @PathVariable(name = "id")long id){
        return new ResponseEntity<>(lessonService.updateLesson(requestDTO, id),HttpStatus.OK);
    }
    @PostMapping("/add-students/{lessonId}")
    public ResponseEntity<Void> addStudents(@RequestBody Set<Long> studentids,@PathVariable(name = "lessonId")Long lessonId){
        lessonService.addStudents(studentids, lessonId);
        return ResponseEntity.ok().build();
    }
}
