package com.example.project.controllers;

import com.example.project.entities.PostEntity;
import com.example.project.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import com.example.project.Dto.SuccessDto;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("get/{id}")
    public PostEntity getById (@PathVariable("id") Long id){
        return postRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Posts" + id + "not found"));
    }

    @GetMapping("/get-all")
    public List<PostEntity> getAll(){
        return postRepository.findAll();
    }

    @PostMapping("/create")
    public PostEntity create(@RequestBody PostEntity newTask){
        return postRepository.save(newTask);
    }


    @PutMapping("update/{id}")
    public PostEntity update(@RequestBody PostEntity post, @PathVariable("id") Long id){
        PostEntity toUpdate = postRepository.findById(id).get();


        if (post.getTitle() != null){
            toUpdate.setTitle(post.getTitle());
        }

        if (post.getContent() != null){
            toUpdate.setContent(post.getContent());
        }


        if (post.getUser() != null){
            toUpdate.setUser(post.getUser());
        }

        if (post.getStatus() != null){
            toUpdate.setStatus(post.getStatus());
        }

        if (post.getImages() != null){
            toUpdate.setImages(post.getImages());
        }


        return postRepository.save(toUpdate);

    }

    @DeleteMapping("delete/{id}")
    public SuccessDto delete(@PathVariable Long id) {
        postRepository.deleteById(id);
        return new SuccessDto(true);
    }

}
