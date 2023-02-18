package com.example.musica.resource;

import com.example.musica.resource.DTO.MusicaDTO;
import com.example.musica.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController()
public class MusicaResource {
    @Autowired
    StorageService storageService;
    @GetMapping()
    public MusicaDTO obterMusica(){
        return new MusicaDTO("thiago");
    }
    @PostMapping("/musicas")
    public MusicaDTO criarMusica(@RequestParam(value = "file",required = true) MultipartFile file) throws Exception{

            storageService.store(file);


        return new MusicaDTO("thiago");
    }
}
