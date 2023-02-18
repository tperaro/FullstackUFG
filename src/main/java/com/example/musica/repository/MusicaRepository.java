package com.example.musica.repository;

import com.example.musica.Model.Musica;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicaRepository extends JpaRepository<Musica,Long> {
    List<Musica> findByGenero(String genero);
    List<Musica> findByNome(String nome);
}
