package com.example.betterStudy.service;


import com.example.betterStudy.model.Classroom;
import com.example.betterStudy.model.Lesson;
import com.example.betterStudy.model.Student;
import com.example.betterStudy.model.dto.ClassroomResponseDTO;
import com.example.betterStudy.model.dto.CreateClassroomRequestDTO;
import com.example.betterStudy.model.dto.UpdateClassroomRequestDTO;
import com.example.betterStudy.repository.ClassroomRepository;
import com.example.betterStudy.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.SoftDelete;
import org.springframework.boot.web.servlet.server.Session;
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
public class ClassroomService {
    private final ClassroomRepository classroomRepository;
    private final LessonRepository lessonRepository;

    public ClassroomResponseDTO findById(long id){
        Classroom classroom = classroomRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return ClassroomResponseDTO.builder()
                .id(id)
                .name(classroom.getName())
                .location(classroom.getLocation())
                .lessons(classroom.getLessons())
                .build();
    }
    public Page<ClassroomResponseDTO> findAll(Pageable pageable){
        Page<Classroom> pageOfClassroom = classroomRepository.findAll(pageable);
        Page<ClassroomResponseDTO> classroomResponsePageDTO = pageOfClassroom.map(classroom -> {
            ClassroomResponseDTO classroomBuild = ClassroomResponseDTO.builder()
                    .id(classroom.getId())
                    .name(classroom.getName())
                    .location(classroom.getLocation())
                    .lessons(classroom.getLessons())
                    .build();
            return classroomBuild;
        });
        return classroomResponsePageDTO;
    }
    @Transactional
    public ClassroomResponseDTO save(CreateClassroomRequestDTO requestDTO){
        Classroom classroomBuild = Classroom.builder()
                .name(requestDTO.getName())
                .location(requestDTO.getLocation())
                .build();

        Classroom save = classroomRepository.save(classroomBuild);

        return ClassroomResponseDTO.builder()
                .id(save.getId())
                .name(classroomBuild.getName())
                .location(classroomBuild.getLocation())
                .build();
    }
    @Transactional
    public ClassroomResponseDTO updateClassroom(UpdateClassroomRequestDTO requestDTO, long id){

        Classroom classroom = classroomRepository.findById(id).orElseThrow(NoSuchElementException::new);

        Classroom classroomBuild = Classroom.builder()
                .id(id)
                .name(requestDTO.getName())
                .location(requestDTO.getLocation())
                .build();
        classroomRepository.delete(classroom);
        Classroom save = classroomRepository.save(classroomBuild);

        return ClassroomResponseDTO.builder()
                .id(save.getId())
                .name(classroomBuild.getName())
                .location(classroomBuild.getLocation())
                .build();
    }
    @Transactional
    public void deleteLessons(Set<Long> lessonsId, long classroomId){
        List<Lesson> allById = lessonRepository.findAllById(lessonsId);
        Classroom classroom = classroomRepository.findById(classroomId).orElseThrow(NoSuchElementException::new);
        for (Lesson lesson: allById) {
            classroom.getLessons().remove(lesson);
        }
    }
    @Transactional
    public void addLessons(Set<Long> lessonsId, long classroomId){
        List<Lesson> allById = lessonRepository.findAllById(lessonsId);
        Optional<Classroom> byId = classroomRepository.findById(classroomId);
        for (Lesson lesson: allById) {
            byId.get().getLessons().add(lesson);
        }
    }
    @Transactional
    public void delete(long id){
    classroomRepository.deleteById(id);
    }
}
