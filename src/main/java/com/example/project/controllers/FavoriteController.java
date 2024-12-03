package com.example.project.controllers;


import com.example.project.Dto.FavoriteDto;
import com.example.project.Dto.SuccessDto;
import com.example.project.entities.FavoriteEntity;
import com.example.project.repositories.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @GetMapping("/get-all")
    public List<FavoriteEntity> getAll(@RequestParam(value = "name", required = false) String name){
        if (name != null){
            return favoriteRepository.findAllByNameContainingIgnoreCase(name);
        }else {
            return favoriteRepository.findAll();
        }
    }

    @GetMapping("get/{id}")
    public FavoriteEntity getById (@PathVariable("id") Long id){
        return favoriteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entries" + id + "not found"));
    }

    @PostMapping("/create")
    public FavoriteEntity create (@RequestBody FavoriteEntity favoriteEntity){
        return favoriteRepository.save(favoriteEntity);
    }

    @PutMapping("/update/{id}")
    public FavoriteEntity update(@RequestBody FavoriteDto favoriteDto, @PathVariable Long id){
        FavoriteEntity toUpdate = favoriteRepository.findById(id).get();
        if (favoriteDto.getUserId() != null){
            toUpdate.setUser(favoriteDto.getUserId());
        }

        if (favoriteDto.getPostId() != null){
            toUpdate.setPost(favoriteDto.getPostId());
        }

        if (favoriteDto.getUpdatedAt() != null){
            toUpdate.setUpdated_at(favoriteDto.getUpdatedAt());
        }
        return favoriteRepository.save(toUpdate);
    }

    @DeleteMapping("delete/{id}")
    public SuccessDto delete(@PathVariable Long id) {
        favoriteRepository.deleteById(id);
        return new SuccessDto(true);
    }

}
