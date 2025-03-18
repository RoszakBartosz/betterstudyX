package com.example.betterStudy.controller;

import com.example.betterStudy.model.dto.ClassroomResponseDTO;
import com.example.betterStudy.model.dto.CreateClassroomRequestDTO;
import com.example.betterStudy.model.dto.UpdateClassroomRequestDTO;
import com.example.betterStudy.service.ClassroomService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/classroom")

public class ClassroomController {
    @Autowired
    private  ClassroomService classroomService;

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<ClassroomResponseDTO> findById(@PathVariable(name = "id")long id){
        return new ResponseEntity<>(classroomService.findById(id), HttpStatus.OK);
    }
    @GetMapping("/find-all")
    public ResponseEntity<Page<ClassroomResponseDTO>> findAll(@PageableDefault Pageable pageable){
        return new ResponseEntity<>(classroomService.findAll(pageable), HttpStatus.OK);
    }
    @PostMapping("/save")
    // todo trzeba pododawac walidajce w dto's, by ci ktos nie wjebal pustego stringa itp
    public ResponseEntity<ClassroomResponseDTO> save(@RequestBody CreateClassroomRequestDTO requestDTO){
        return new ResponseEntity<>(classroomService.save(requestDTO), HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ClassroomResponseDTO> updateClassroom(@RequestBody UpdateClassroomRequestDTO requestDTO, @PathVariable(name = "id")long id){
        return new ResponseEntity<>(classroomService.updateClassroom(requestDTO, id), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id")long id){
        classroomService.delete(id);
        return ResponseEntity.ok().build();
    }
}
