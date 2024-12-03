package com.example.project.Dto;


import com.example.project.entities.PostEntity;
import com.example.project.entities.UserEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class FavoriteDto {

    private UserEntity userId;

    private PostEntity postId;

    private LocalDateTime updatedAt;

}
