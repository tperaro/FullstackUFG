package com.example.musica.resource.DTO;

import com.example.musica.Model.Musica;

public class MusicaDTO {
    private Long id;
    private String name;
    private String description;
    private String genre;
    private String photoFileName;
    private String audioFileName;

    public MusicaDTO(Long id, String name, String description, String genre, String photoFileName, String audioFileName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.photoFileName = photoFileName;
        this.audioFileName = audioFileName;
    }
    
    public MusicaDTO() {
    }

    public static MusicaDTO fromMusic(Musica music) {
        return new MusicaDTO(
            music.getId(),
            music.getNome(),
            music.getDescricao(),
            music.getGenero(),
            music.getFoto(),
            music.getMusica()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPhotoFileName() {
        return photoFileName;
    }

    public void setPhotoFileName(String photoFileName) {
        this.photoFileName = photoFileName;
    }

    public String getAudioFileName() {
        return audioFileName;
    }

    public void setAudioFileName(String audioFileName) {
        this.audioFileName = audioFileName;
    }
    
}
