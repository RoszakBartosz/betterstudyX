package com.example.betterStudy.Controller;

import com.example.betterStudy.controller.StudentController;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(StudentController.class)
@AutoConfigureMockMvc
public class ClassroomControllerTest {
}
