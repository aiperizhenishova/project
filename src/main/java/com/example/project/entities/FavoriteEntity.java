package com.example.project.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "favorite")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)    //связывает с таб user
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)   //связывает с таб posts
    private PostEntity post;


    @Column(nullable = false, updatable = false)
    private LocalDateTime updated_at = LocalDateTime.now();
}
