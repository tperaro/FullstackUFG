package com.example.blog.repository;

import com.example.blog.Model.Comentario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario,Long> {
    List<Comentario> findByPostId(Long postId);
}
