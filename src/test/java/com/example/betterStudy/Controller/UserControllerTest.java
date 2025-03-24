package com.example.betterStudy.Controller;

import com.example.betterStudy.BetterStudyApplication;
import com.example.betterStudy.controller.StudentController;
import com.example.betterStudy.controller.UserController;
import com.example.betterStudy.model.dto.RequestUserDTO;
import com.example.betterStudy.model.dto.UserResponseDTO;
import com.example.betterStudy.model.enums.UserRole;
import com.example.betterStudy.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;
    private UserResponseDTO userResponse;

    @BeforeEach
    void setUp() {
        userResponse = new UserResponseDTO(1L, "test@example.com","affsdf", UserRole.STUDENT);
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    void findById_shouldReturnUser_whenUserExists() throws Exception {
        when(userService.findById(1)).thenReturn(userResponse);
        mockMvc.perform(get("/user/find-by-id/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    void findById_shouldReturnNotFound_whenUserDoesNotExist() throws Exception {
        when(userService.findById(1L)).thenThrow(new NoSuchElementException());
        mockMvc.perform(get("/user/find-by-id/1L"))
                .andExpect(status().isBadRequest());
    }

//    @Test
//    @WithMockUser(username = "admin", password = "admin")
//    void createUser_shouldReturnCreatedUser() throws Exception {
//        RequestUserDTO request = new RequestUserDTO("test@example.com", "password123", UserRole.ADMIN);
//        when(userService.createUser(any(RequestUserDTO.class))).thenReturn(userResponse);
//        mockMvc.perform(post("/user/save")
//                        .with(httpBasic("admin", "admin"))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(request)))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.email").value("test@example.com"));
//    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    void createUser_shouldReturnBadRequest_whenInvalidData() throws Exception {
        RequestUserDTO request = new RequestUserDTO("", "", null);
        mockMvc.perform(post("/user/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

//    //TODO delte user
//    @Test
//    @WithMockUser(username = "admin", password = "admin")
//    void deleteUser_ShouldDeleteUserWhenExists() throws Exception {
////        when(userService.deleteById(any())).thenReturn(HttpStatus.OK);
//        mockMvc.perform(delete("/user/delete-by-id/1")
//                        .with(httpBasic("admin", "admin")))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
}
