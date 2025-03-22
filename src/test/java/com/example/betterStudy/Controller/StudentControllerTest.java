package com.example.betterStudy.Controller;

import com.example.betterStudy.controller.StudentController;
import com.example.betterStudy.model.dto.StudentResponseDTO;
import com.example.betterStudy.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.test.context.support.WithMockUser;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@ExtendWith(MockitoExtension.class)
@WebMvcTest(StudentController.class)
@AutoConfigureMockMvc

class StudentControllerTest {
    @MockitoBean
    private StudentService service;
    @Autowired
    private MockMvc mockMvc;
    @Test
    @WithMockUser(username = "admin", password = "admin")
    void shouldFindAllStudentsWithPagination() throws Exception {
        long id = 1l;
        String name = "maćko";
        String lastname = "b";
        String email = "b@b.b";
        String grade = "4";
        StudentResponseDTO stu = StudentResponseDTO.builder().id(id)
                .grade(grade)
                .email(email)
                .lastName(lastname)
                .name(name)
                .build();
        long id1 = 5l;
        String name1 = "staśko";
        StudentResponseDTO stu1 = StudentResponseDTO.builder().id(id1)
                .grade(grade)
                .email(email)
                .lastName(lastname)
                .name(name1)
                .build();
        List<StudentResponseDTO> responseDTOS = new ArrayList<>();
        responseDTOS.add(stu1);
        responseDTOS.add(stu);
        Page<StudentResponseDTO> responseDTOPage = new PageImpl<>(responseDTOS);
        when(service.findAll(PageRequest.of(2,5))).thenReturn(responseDTOPage);
        mockMvc.perform(get("/student/find-all?size=5&page=2")
                .with(httpBasic("admin", "admin")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(5L))
                .andExpect(jsonPath("$.content[0].name").value("staśko"))
                .andExpect(jsonPath("$.content[0].email").value("b@b.b"))
                .andExpect(jsonPath("$.content[1].id").value(1L))
                .andExpect(jsonPath("$.content[1].name").value("maćko"))
                .andExpect(jsonPath("$.content[1].email").value("b@b.b"));
    }
    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void findById_shouldReturnDTO() throws Exception {
        StudentResponseDTO build = StudentResponseDTO.builder()
                .name(" ")
                .lastName("Bajcar")
                .id(1l)
                .email("kajak")
                .grade("student biura podróży")
                .build();
        when(service.findById(1L)).thenReturn(build);
        mockMvc.perform(get("/student/find-by-id/1"))
                .andExpect(status().isOk());

    }


//    @Test
//    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
//    void save_shouldThrowNotEmptyName2() throws Exception {
//        CreateStudentRequestDTO build = CreateStudentRequestDTO.builder()
//                .name(" ")
//                .lastName("Bajcar")
//                .email("kajak")
//                .grade("student biura podróży")
//                .build();
//
//        StudentResponseDTO build2 = StudentResponseDTO.builder()
//                .name(" ")
//                .lastName("Bajcar")
//                .id(1)
//                .email("kajak")
//                .grade("student biura podróży")
//                .build();
//
//        when(service.save(any())).thenThrow(NotFoundStudentException.class);
//
//        mockMvc.perform(post("/student/save")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(build))
//                        .with(httpBasic("admin", "admin")))
//                .andDo(print())
//                .andExpect(status().isBadRequest());
//    }


}