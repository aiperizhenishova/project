package com.example.project.Dto;

import com.example.project.entities.UserEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PostDto {

    private Long id;
    private String title;
    private String content;
    private UserEntity user;
    private String status;
    private String images;
    private LocalDateTime created_at = LocalDateTime.now();
    private LocalDateTime updated_at = LocalDateTime.now();
}
