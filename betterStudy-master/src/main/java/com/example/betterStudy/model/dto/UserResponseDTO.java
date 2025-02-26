package com.example.betterStudy.model.dto;

import com.example.betterStudy.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserResponseDTO {
    private long id;
    private String email;
    private String password;
    private UserRole userRole;
}
