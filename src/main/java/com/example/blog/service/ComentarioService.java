package com.example.blog.service;

import com.example.blog.Model.Comentario;
import com.example.blog.repository.ComentarioRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {
    @Autowired
    private ComentarioRepository comentarioRepository;
    public Comentario consultarComentarioPorId(Long id){
        Comentario comentario = comentarioRepository.findById(id).get();
        return comentario;
    }

    public Comentario salvarComentario(Comentario comentario){
        try{
            comentarioRepository.save(comentario);

        }
        catch (RuntimeException exception){
            throw new RuntimeException("Não foi possível criar este comentário");
        }
        return comentario;
    }
    public Comentario likeComentarioPorId(Long id){
        Comentario comentario= consultarComentarioPorId(id);
        comentario.daUmLikeNoComentario();
        return salvarComentario(comentario);
    }

    public void deletaComentarioPorId(Long id){
        if (comentarioRepository.existsById(id)){
            comentarioRepository.deleteById(id);
        }
    }

    public List<Comentario> encontrarComentariosPorPostId(Long postId) {
        return comentarioRepository.findByPostId(postId);
    }

}
