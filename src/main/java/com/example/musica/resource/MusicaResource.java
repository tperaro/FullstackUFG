package com.example.musica.resource;

import com.example.musica.resource.DTO.MusicaDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MusicaResource {
    @GetMapping()
    public MusicaDTO obterMusica(){
        return new MusicaDTO("thiago");
    }
}
