package com.example.betterStudy.service;

import com.example.betterStudy.model.Student;
import com.example.betterStudy.model.dto.CreateStudentRequestDTO;
import com.example.betterStudy.model.dto.StudentResponseDTO;
import com.example.betterStudy.model.dto.UpdateStudentRequestDTO;
import com.example.betterStudy.model.exception.InvalidEmailException;
import com.example.betterStudy.model.exception.NotFoundStudentException;
import com.example.betterStudy.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.SoftDelete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    @Transactional
    public StudentResponseDTO save(CreateStudentRequestDTO studentRequestDTO){
        if (studentRepository.findByEmail(studentRequestDTO.getEmail())==null) {
            throw new InvalidEmailException("this email already exists ");
        }
        Student buildStudent = Student.builder()
                .name(studentRequestDTO.getName())
                .lastName(studentRequestDTO.getLastName())
                .email(studentRequestDTO.getEmail())
                .grade(studentRequestDTO.getGrade())
                .registrationDate(LocalDate.now())
                .build();
        studentRepository.save(buildStudent);
        return StudentResponseDTO.builder()
                .id(buildStudent.getId())
                .name(buildStudent.getName())
                .lastName(buildStudent.getLastName())
                .email(buildStudent.getEmail())
                .grade(buildStudent.getGrade())
                .registrationDate(buildStudent.getRegistrationDate())
                .build();
    }
    public StudentResponseDTO findById(Long id){
        Student student = studentRepository.findById(id).orElseThrow(NotFoundStudentException::new);
        return StudentResponseDTO.builder()
                .id(student.getId())
                .name(student.getName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .grade(student.getGrade())
                .registrationDate(student.getRegistrationDate())
                .build();
    }

    public Page<StudentResponseDTO> findAll(Pageable pageable){
        if (studentRepository.findAll().isEmpty()){
            throw new NotFoundStudentException("the set of students is empty ");
        }
        Page<Student> all = studentRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        Page<StudentResponseDTO> pageStudentResponseDTO = all.map(student -> {
            return StudentResponseDTO.builder()
                    .id(student.getId())
                    .name(student.getName())
                    .email(student.getEmail())
                    .grade(student.getGrade())
                    .lastName(student.getLastName())
                    .registrationDate(student.getRegistrationDate())
                    .build();
        });
        return pageStudentResponseDTO;
    }
    @Transactional
    public StudentResponseDTO updateStudent(UpdateStudentRequestDTO requestDTO, long id){
        if (studentRepository.findByEmail(requestDTO.getEmail())==null) {
            throw new InvalidEmailException("this email already exists ");
        }
        Student studentById = studentRepository.findById(id).orElseThrow(NotFoundStudentException::new);
        Student studentBuild = Student.builder()
                .id(id)
                .name(requestDTO.getName())
                .lastName(requestDTO.getLastName())
                .email(requestDTO.getEmail())
                .grade(requestDTO.getGrade())
                .lessons(requestDTO.getLessons())
                .registrationDate(requestDTO.getRegistrationDate())
                .build();
        studentRepository.save(studentBuild);
        studentRepository.delete(studentById);
        int lessons;
        return StudentResponseDTO.builder()
                .id(studentBuild.getId())
                .name(studentBuild.getName())
                .lastName(studentBuild.getLastName())
                .email(studentBuild.getEmail())
                .grade(studentBuild.getGrade())
                .registrationDate(studentBuild.getRegistrationDate())
                .build();
    }
    @Transactional
    @SoftDelete
    public void delete(long id){

        if (studentRepository.findById(id)==null){
            throw new NotFoundStudentException("cannot find this student ");
        }
        studentRepository.deleteById(id);
    }

}
