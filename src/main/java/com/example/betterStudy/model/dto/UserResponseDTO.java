package com.example.betterStudy.model.dto;

import com.example.betterStudy.model.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserResponseDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String email;
    @NotBlank(message = "The password cannot be empty ")
    private String password;
    @Enumerated(value = EnumType.STRING)
    @NotNull(message = "UserRole cannot be null ")
    private UserRole userRole;
}
