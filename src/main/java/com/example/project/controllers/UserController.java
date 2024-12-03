package com.example.project.controllers;


import com.example.project.Dto.SuccessDto;
import com.example.project.Dto.UserDto;
import com.example.project.entities.UserEntity;
import com.example.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/get-all")
    public List<UserEntity> getAll(@RequestParam(value = "name", required = false) String name){
        if (name != null){
            return userRepository.findAllByUsernameContainingIgnoreCase(name);
        }else {
            return userRepository.findAll();
        }
    }

    @GetMapping("get/{id}")
    public UserEntity getById (@PathVariable("id") Long id){
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User" + id + "not found"));
    }

    @PostMapping("/create")
    public UserEntity create (@RequestBody UserEntity userEntity){
        return userRepository.save(userEntity);
    }



    @PutMapping("/update/{id}")
    public UserEntity update(@RequestBody UserDto userDto, @PathVariable Long id){
        UserEntity toUpdate = userRepository.findById(id).get();

        if (userDto.getUsername() != null){
            toUpdate.setUsername(userDto.getUsername());
        }

        if (userDto.getEmail() != null){
            toUpdate.setEmail(userDto.getEmail());
        }

        if (userDto.getPassword() != null){
            toUpdate.setPassword(userDto.getPassword());
        }

        toUpdate.setUpdated_at(LocalDateTime.now());   // всегда обновляется при изменении данных

        return userRepository.save(toUpdate);


    }


    @DeleteMapping("delete/{id}")
    public SuccessDto delete(@PathVariable Long id) {
        userRepository.deleteById(id);
        return new SuccessDto(true);
    }
}
