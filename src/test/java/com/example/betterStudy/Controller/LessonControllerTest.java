package com.example.betterStudy.Controller;


import com.example.betterStudy.controller.LessonController;
import com.example.betterStudy.controller.StudentController;
import com.example.betterStudy.model.Lesson;
import com.example.betterStudy.model.dto.LessonResponseDTO;
import com.example.betterStudy.repository.LessonRepository;
import com.example.betterStudy.repository.StudentRepository;
import com.example.betterStudy.repository.TeacherRepository;
import com.example.betterStudy.service.LessonService;
import com.example.betterStudy.service.StudentService;
import com.fasterxml.jackson.annotation.JsonFormat;
import liquibase.lockservice.LockService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.startsWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.text.Format;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.zone.ZoneRules;
import java.util.Formatter;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(LessonController.class)
@AutoConfigureMockMvc
public class LessonControllerTest {
    @MockitoBean
    private LessonService service;
    @Autowired
    private MockMvc mockMvc;
    @Mock
    TeacherRepository teacherRepository;
    @Mock
    StudentRepository studentRepository;
    @Mock
    LessonRepository lessonRepository;



    @Test
    @WithMockUser(username = "admin", password = "admin")
    void findById_shouldReturnResponseDTO() throws Exception {
        LocalDateTime lessonDateTime;
        lessonDateTime = LocalDateTime.of(2025, 05, 22, 14, 35);
        LessonResponseDTO ttopic = LessonResponseDTO.builder()
                .studentsIds(List.of(2l, 3l))
                .teacherid(2l)
                .classroomid(2)
                .id(1l)
                .lessonDateTime(lessonDateTime)
                .topic("ttopic")
                .build();

        when(service.findById(1l)).thenReturn(ttopic);
        mockMvc.perform(get("/lesson/find-by-id/1").with(httpBasic("admin", "admin")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.topic").value("ttopic"))
                .andExpect(jsonPath("$.id").value(1l))
                .andExpect(jsonPath("$.classroomid").value(2l))
                .andExpect(jsonPath("$.studentsIds[0]").value(2l))
                .andExpect(jsonPath("$.studentsIds[1]").value(3l))
                .andExpect(jsonPath("$.lessonDateTime").value(startsWith("2025-05-22T14:35:00")));
    }
    @Test
    @WithMockUser(username = "admin", password = "admin")
    void findAll_shouldReturnPageOfDTO() throws Exception {
        LocalDateTime lessonDateTime;
        lessonDateTime = LocalDateTime.of(2025, 05, 22, 14, 35);
        LessonResponseDTO ttopic = LessonResponseDTO.builder()
                .studentsIds(List.of(2l, 3l))
                .teacherid(2l)
                .classroomid(2)
                .id(1l)
                .lessonDateTime(lessonDateTime)
                .topic("ttopic")
                .build();
        lessonDateTime = LocalDateTime.of(2025, 06, 22, 14, 35);
        LessonResponseDTO polish = LessonResponseDTO.builder()
                .studentsIds(List.of(4l, 5l))
                .teacherid(2l)
                .classroomid(2)
                .id(2l)
                .lessonDateTime(lessonDateTime)
                .topic("polish")
                .build();
        lessonDateTime = LocalDateTime.of(2025, 07, 22, 14, 35);
        LessonResponseDTO english = LessonResponseDTO.builder()
                .studentsIds(List.of(6l, 7l))
                .teacherid(2l)
                .classroomid(2)
                .id(3l)
                .lessonDateTime(lessonDateTime)
                .topic("english")
                .build();

        Page pageOfDTO = new PageImpl(List.of(ttopic, polish, english));

        when(service.findAll(any(Pageable.class))).thenReturn(pageOfDTO);
        mockMvc.perform(get("/lesson/find-all?page=2&size=2").with(httpBasic("admin", "admin")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].topic").value("ttopic"))
                .andExpect(jsonPath("$.content[0].id").value(1l))
                .andExpect(jsonPath("$.content[0].classroomid").value(2l))
                .andExpect(jsonPath("$.content[0].studentsIds[0]").value(2l))
                .andExpect(jsonPath("$.content[0].studentsIds[1]").value(3l))
                .andExpect(jsonPath("$.content[0].lessonDateTime").value(startsWith("2025-05-22T14:35:00")))
                .andExpect(jsonPath("$.content[1].topic").value("polish"))
                .andExpect(jsonPath("$.content[1].id").value(2l))
                .andExpect(jsonPath("$.content[1].classroomid").value(2l))
                .andExpect(jsonPath("$.content[1].studentsIds[0]").value(4l))
                .andExpect(jsonPath("$.content[1].studentsIds[1]").value(5l))
                .andExpect(jsonPath("$.content[1].lessonDateTime").value(startsWith("2025-06-22T14:35:00")))
                .andExpect(jsonPath("$.content[2].topic").value("english"))
                .andExpect(jsonPath("$.content[2].id").value(3l))
                .andExpect(jsonPath("$.content[2].classroomid").value(2l))
                .andExpect(jsonPath("$.content[2].studentsIds[0]").value(6l))
                .andExpect(jsonPath("$.content[2].studentsIds[1]").value(7l))
                .andExpect(jsonPath("$.content[2].lessonDateTime").value(startsWith("2025-07-22T14:35:00")));
    }

}

