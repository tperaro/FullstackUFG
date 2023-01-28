package com.example.blog.resource;

import com.example.blog.Model.Post;

import com.example.blog.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@CrossOrigin("*")
@RestController
@RequestMapping("/post")
public class PostResource {

    @Autowired
    private PostService postService;


    @GetMapping("/{id}")
    public ResponseEntity<Post> buscarPostPorId(@PathVariable Long id){
        Post post = postService.consultarPost(id);
        return ResponseEntity.ok(post);

    }

    @PutMapping()
    public Post alterarPost(@Valid @RequestBody Post post){return postService.salvarPost(post);}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post criarPost(@RequestBody @Valid Post post ){return postService.salvarPost(post);}

    @PatchMapping("/{id}")
    public Post daUmLike(@PathVariable Long id){return postService.likePostPorId(id);}

    @DeleteMapping
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletaPost(@PathVariable Long id){ postService.deletaPost(id);}
}
