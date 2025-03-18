package com.example.betterStudy.service;

import com.example.betterStudy.model.Classroom;
import com.example.betterStudy.model.Lesson;
import com.example.betterStudy.model.Teacher;
import com.example.betterStudy.model.dto.CreateLessonRequestDTO;
import com.example.betterStudy.repository.ClassroomRepository;
import com.example.betterStudy.repository.LessonRepository;
import com.example.betterStudy.repository.StudentRepository;
import com.example.betterStudy.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class LessonServiceTest {

    @Mock
    private final LessonRepository lessonRepository;
    @Mock
    private final StudentRepository studentRepository;
    @Mock
    private final TeacherRepository teacherRepository;
    @Mock
    private final ClassroomRepository classroomRepository;
    @Mock
    private final EmailSenderService emailSenderService;
    @InjectMocks
    private final LessonService service;

    @Test
    void save_ShouldSaveALesson(){
        long teacherId = 1l;
        long classroomId = 1l;
        LocalDateTime now = LocalDateTime.now();
        String topic = "saaa";
        CreateLessonRequestDTO createLessonRequestDTO = new CreateLessonRequestDTO(teacherId, classroomId, now, topic);
        Classroom classroom = Classroom.builder().id(classroomId)
                .location("a")
                .name("asds")
                .build();

        Teacher build = Teacher.builder().id(teacherId)
                .grade("ads")
                .email("sdad")
                .lastName("dfsfd")
                .name("fds")
                .build();

        Lesson build1 = Lesson.builder().topic(topic)
                .classroom(classroom)
                .teacher(build)
                .lessonDateTime(now)
                .build();
        Lesson.builder().lessonDateTime(now);
        Mockito.when(classroomRepository.findById(classroomId)).thenReturn(Optional.ofNullable(classroom));
        Mockito.when(teacherRepository.findById(teacherId)).thenReturn(Optional.ofNullable(build));
        service.save(createLessonRequestDTO);
        Mockito.verify(lessonRepository).save(build1);

    }

}
