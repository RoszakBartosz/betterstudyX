package com.example.betterStudy.service;

import com.example.betterStudy.model.Teacher;
import com.example.betterStudy.model.dto.CreateTeacherRequestDTO;
import com.example.betterStudy.model.dto.TeacherResponseDTO;
import com.example.betterStudy.model.dto.UpdateTeacherRequestDTO;
import com.example.betterStudy.model.exception.NotFoundTeacherException;
import com.example.betterStudy.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherResponseDTO findById(long id){
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new NotFoundTeacherException("do not find any objects with id: " + id));{
            return TeacherResponseDTO.builder()
                    .id(id)
                    .name(teacher.getName())
                    .lastName(teacher.getLastName())
                    .email(teacher.getEmail())
                    .grade(teacher.getGrade())
                    .rate(teacher.getRate())
                    .build();
        }
    }
    public Page<TeacherResponseDTO> findAll(Pageable pageable, Sort sort){
        Page<Teacher> all = teacherRepository.findAll(pageable);
        Page<TeacherResponseDTO> pageOfDTO = all.map(teacher -> {
            return TeacherResponseDTO.builder()
                    .id(teacher.getId())
                    .name(teacher.getName())
                    .lastName(teacher.getLastName())
                    .email(teacher.getEmail())
                    .grade(teacher.getGrade())
                    .rate(teacher.getRate())
                    .build();
        });
        return pageOfDTO;
    }
    @Transactional
    public TeacherResponseDTO save(CreateTeacherRequestDTO requestDTO){
        Teacher teacherBuild = Teacher.builder()
                .name(requestDTO.getName())
                .lastName(requestDTO.getLastName())
                .email(requestDTO.getEmail())
                .grade(requestDTO.getGrade())
                .rate(requestDTO.getRate())
                .build();
        teacherRepository.save(teacherBuild);
        return TeacherResponseDTO.builder()
                .id(teacherBuild.getId())
                .name(teacherBuild.getName())
                .lastName(teacherBuild.getLastName())
                .email(teacherBuild.getEmail())
                .grade(teacherBuild.getGrade())
                .rate(teacherBuild.getRate())
                .build();
    }
    @Transactional
    public TeacherResponseDTO updateTeacher(UpdateTeacherRequestDTO requestDTO, long id){
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new NoSuchElementException("did not find any teacher with id: " + id));
        Teacher teacherBuild = Teacher.builder()
                .id(id)
                .name(requestDTO.getName())
                .lastName(requestDTO.getLastName())
                .email(requestDTO.getEmail())
                .grade(requestDTO.getGrade())
                .rate(requestDTO.getRate())
                .build();
        teacherRepository.save(teacherBuild);
        teacherRepository.delete(teacher);
        return TeacherResponseDTO.builder()
                .id(teacherBuild.getId())
                .name(teacherBuild.getName())
                .lastName(teacherBuild.getLastName())
                .email(teacherBuild.getEmail())
                .grade(teacherBuild.getGrade())
                .rate(teacherBuild.getRate())
                .build();
    }
    @Transactional
    public void delete(long id){
        teacherRepository.deleteById(id);
    }

    
}
