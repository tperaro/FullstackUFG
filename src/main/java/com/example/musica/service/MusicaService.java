package com.example.musica.service;

import com.example.musica.Model.Musica;
import com.example.musica.repository.MusicaRepository;
import com.example.musica.service.exceptions.MusicNotFoundException;
import com.example.musica.service.exceptions.StorageServiceException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MusicaService {
  @Autowired
  private MusicaRepository musicaRepository;

  @Autowired
  private StorageService storageService;

  public Musica create(String name, String description, String genre, MultipartFile thumbnail, MultipartFile audio) throws Exception {
    var thumbnailName = storageService.store(thumbnail);
    var audioName = storageService.store(audio);
    var musica = new Musica(null, name, description, thumbnailName, audioName, genre);
    return musicaRepository.save(musica);
  }

  public Musica update(
    Long id,
    @Nullable String name,
    @Nullable String description,
    @Nullable String genre,
    @Nullable MultipartFile thumbnail,
    @Nullable MultipartFile audio
  ) throws MusicNotFoundException, StorageServiceException {
    var foundMusic = musicaRepository.findById(id);
    if (!foundMusic.isPresent()) { throw new MusicNotFoundException(); }
    Musica currentMusicState = foundMusic.get();
    List<String> filesToDelete = new ArrayList<>();
    if (name != null) {
      currentMusicState.setNome(name);
    }
    if (description != null) {
      currentMusicState.setDescricao(description);
    }
    if (genre != null) {
      currentMusicState.setDescricao(genre);
    }
    if (thumbnail != null) {
      String thumbnailName =  storageService.store(thumbnail);
      filesToDelete.add(currentMusicState.getFoto());
      currentMusicState.setFoto(thumbnailName);
    }
    if (audio != null) {
      String audioName = storageService.store(audio);
      filesToDelete.add(currentMusicState.getMusica());
      currentMusicState.setMusica(audioName);
    }
    Musica savedMusic = musicaRepository.save(currentMusicState);
    for (String file : filesToDelete) {
      storageService.delete(file);
    }
    return savedMusic;
  }

  public void deleteById(Long id) {
    var foundMusic = musicaRepository.findById(id);
    if (foundMusic.isPresent()) {
      musicaRepository.deleteById(id);
      try {
        storageService.delete(foundMusic.get().getFoto());
        storageService.delete(foundMusic.get().getMusica());
      } catch (Exception e) {}
    }
  }

  public List<Musica> findAll() {
    return musicaRepository.findAll();
  }

}
