package com.example.musica.repository;

import com.example.musica.Model.Musica;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicaRepository extends JpaRepository<Musica,Long> {}
