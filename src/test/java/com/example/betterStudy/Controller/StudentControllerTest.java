package com.example.betterStudy.Controller;

import com.example.betterStudy.controller.StudentController;
import com.example.betterStudy.repository.StudentRepository;
import com.example.betterStudy.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
@WebMvcTest(StudentController.class)
class StudentControllerTest {
    @Mock
    private final StudentService service;
    private final MockMvc mockMvc;

    @Test
    void shouldFindAllStudentsWithPagination() throws Exception {
//        //TODO jesli tam wjedize
//
//        //jesli wjedzie page=2
//
//        mockMvc.perform(get("/student/find-all?size=5&page=2"))
//                .andExpect(status().isOk())
//                .endExpect(content("[0]").equals())
    }

}