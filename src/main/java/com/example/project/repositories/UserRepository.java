package com.example.project.repositories;

import com.example.project.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findAllByUsernameContainingIgnoreCase(String name);
}
