package com.example.musica.service;

import com.example.musica.Model.Musica;
import com.example.musica.repository.MusicaRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MusicaService {
    @Autowired
    private MusicaRepository musicaRepository;
    public Musica consultarComentarioPorId(Long id){
        Musica musica = musicaRepository.findById(id).get();
        return musica;
    }

    public Musica salvarComentario(Musica musica){
        try{
            musicaRepository.save(musica);

        }
        catch (RuntimeException exception){
            throw new RuntimeException("Não foi possível criar este comentário");
        }
        return musica;
    }
    public Musica likeComentarioPorId(Long id){
        Musica musica = consultarComentarioPorId(id);
        musica.daUmLikeNoComentario();
        return salvarComentario(musica);
    }

    public void deletaComentarioPorId(Long id){
        if (musicaRepository.existsById(id)){
            musicaRepository.deleteById(id);
        }
    }

    public List<Musica> encontrarComentariosPorPostId(Long postId) {
        return musicaRepository.findByPostId(postId);
    }

}
