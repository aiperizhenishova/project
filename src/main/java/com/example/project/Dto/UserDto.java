package com.example.project.Dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder

public class UserDto {
    private Long id;
    private String username;
    private String email;
    private Long password;
    private LocalDateTime created_at = LocalDateTime.now();
    private LocalDateTime updated_at = LocalDateTime.now();
}
