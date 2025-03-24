package com.example.betterStudy.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)

public class EmailSenderServiceTest {
    @Mock
    private EmailSenderService emailSenderService;
    @InjectMocks
    private LessonService lessonService;

    @Test
    void sendEmail_shouldSendAnEmailWithParameters(){
        LocalTime localTime = LocalTime.of(11,11);

        try (MockedStatic<LocalTime> mockedStatic = mockStatic(LocalTime.class)) {
            mockedStatic.when(LocalTime::now).thenReturn(localTime);


            String email = "aa@a.a";
            String subject = "New Lesson is created.";
            String text = "Please confirm your attendance.\n This message was created automatically at: " + localTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));


            lessonService.sendNewLessonsEmail(email);
            Mockito.verify(emailSenderService).sendEmail(email, subject, text);
        }
    }
}
