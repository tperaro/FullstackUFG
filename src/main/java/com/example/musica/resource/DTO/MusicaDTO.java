package com.example.musica.resource.DTO;
public class MusicaDTO {
    private String nome;

    public MusicaDTO(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
