package com.example.betterStudy.model.dto;

import com.example.betterStudy.model.enums.UserRole;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RequestUserDTO {
    @NotBlank(message = "Email cannot be empty or blank!")
    private String email;
    @NotBlank(message = "Password cannot be empty or blank! ")
    private String password;
    @NotNull(message = "UserRole cannot be null! ")
    @Enumerated
    private UserRole userRole;
}
