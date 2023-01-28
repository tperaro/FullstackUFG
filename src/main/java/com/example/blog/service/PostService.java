package com.example.blog.service;

import com.example.blog.Model.Post;
import com.example.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post consultarPost(Long id){
        Post post = postRepository.findById(id).get();
        return post;
    }

    public Post salvarPost(Post post){
        try {
            postRepository.save(post);
        }
        catch (RuntimeException ex){
            throw  new RuntimeException("Nao foi possivel criar este post");

        }
       return post;

    }
    public Post likePostPorId(Long id){
        Post post = consultarPost(id);
        post.daUmLike();
        return salvarPost(post);
    }
    public void deletaPost(Long id){
            if(postRepository.existsById(id))
             postRepository.deleteById(id);
    }
}
