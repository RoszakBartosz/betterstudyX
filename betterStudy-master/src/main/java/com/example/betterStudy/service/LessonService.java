package com.example.betterStudy.service;

import com.example.betterStudy.model.Lesson;
import com.example.betterStudy.model.Student;
import com.example.betterStudy.model.dto.CreateLessonRequestDTO;
import com.example.betterStudy.model.dto.LessonResponseDTO;
import com.example.betterStudy.model.dto.UpdateLessonRequestDTO;
import com.example.betterStudy.repository.LessonRepository;
import com.example.betterStudy.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;
    private final StudentRepository studentRepository;
    public LessonResponseDTO findById(long id){
        Lesson lessonById = lessonRepository.getById(id);
        return LessonResponseDTO.builder()
                .id(lessonById.getId())
                .topic(lessonById.getTopic())
                .lessonDateTime(lessonById.getLessonDateTime())
                .teacher(lessonById.getTeacher())
                .students(lessonById.getStudents())
                .classroom(lessonById.getClassroom())
                .build();
    }
    public Page<LessonResponseDTO> findAll(Pageable pageable){
        Page<Lesson> pageOfLessons = lessonRepository.findAll(PageRequest.of(pageable.getPageSize(), pageable.getPageNumber(), Sort.by("lessonDateTime")));
        Page<LessonResponseDTO> pageOfResponseDTO = pageOfLessons.map(lesson -> {
            LessonResponseDTO build = LessonResponseDTO.builder()
                    .id(lesson.getId())
                    .lessonDateTime(lesson.getLessonDateTime())
                    .topic(lesson.getTopic())
                    .teacher(lesson.getTeacher())
                    .numberOfStudents(lesson.getStudents().size())
                    .classroom(lesson.getClassroom())
                    .build();
            return build;
        });
        return pageOfResponseDTO;
    }
    @Transactional
    public LessonResponseDTO save(CreateLessonRequestDTO requestDTO){
        Lesson lessonBuild = Lesson.builder()
                .topic(requestDTO.getTopic())
                .teacher(requestDTO.getTeacher())
                .classroom(requestDTO.getClassroom())
                .lessonDateTime(requestDTO.getLessonDateTime())
                .build();

        lessonRepository.save(lessonBuild);

        return LessonResponseDTO.builder()
                .id(lessonBuild.getId())
                .topic(lessonBuild.getTopic())
                .lessonDateTime(lessonBuild.getLessonDateTime())
                .classroom(lessonBuild.getClassroom())
                .build();
    }
    @Transactional
    public void addStudents(Set<Long> studentsId, long lessonId){
        List<Student> allById = studentRepository.findAllById(studentsId);
        Optional<Lesson> lessonById = lessonRepository.findById(lessonId);
        for (Student student: allById) {
            lessonById.get().getStudents().add(student);
        }
    }
    @Transactional
    public LessonResponseDTO updateLesson(UpdateLessonRequestDTO requestDTO, long id){
        Lesson lesson = lessonRepository.findById(id).orElseThrow(NoSuchElementException::new);
        Lesson lessonBuild = Lesson.builder()
                .id(id)
                .lessonDateTime(requestDTO.getLessonDateTime())
                .classroom(requestDTO.getClassroom())
                .teacher(requestDTO.getTeacher())
                .topic(requestDTO.getTopic())
                .build();
        lessonRepository.delete(lesson);
        lessonRepository.save(lessonBuild);
        return LessonResponseDTO.builder()
                .id(lessonBuild.getId())
                .topic(lessonBuild.getTopic())
                .lessonDateTime(lessonBuild.getLessonDateTime())
                .classroom(lessonBuild.getClassroom())
                .build();
    }
    @Transactional
    public void deleteStudents(Set<Long> studentsId, long lessonId){
        List<Student> allById = studentRepository.findAllById(studentsId);
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(NoSuchElementException::new);
        for (Student student: allById) {
            lesson.getStudents().remove(student);
        }
    }
    @Transactional
    public void delete(long id){
        lessonRepository.deleteById(id);
    }
}
