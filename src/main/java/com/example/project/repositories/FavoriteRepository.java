package com.example.project.repositories;

import com.example.project.entities.FavoriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Long> {

    List<FavoriteEntity> findByUserId (Long userId);

    List<FavoriteEntity> findAllByPostId(Long postId);

    Optional<FavoriteEntity> findByUserIdAndPostId(Long userId, Long postId);

    void deleteByUserIdAndPostId(Long userId, Long postId);

    List<FavoriteEntity> findAllByNameContainingIgnoreCase(String name);
}
