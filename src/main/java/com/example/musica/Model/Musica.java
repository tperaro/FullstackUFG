package com.example.musica.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "musica")
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String foto;

    @Column(nullable = false)
    private String urlMusica;

    @Column(nullable = false)
    private String genero;

    public Musica(Long id, String nome, String descricao, String foto, String urlMusica, String genero) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.foto = foto;
        this.urlMusica = urlMusica;
        this.genero = genero;
    }

    public Musica() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getUrlMusica() {
        return urlMusica;
    }

    public void setUrlMusica(String urlMusica) {
        this.urlMusica = urlMusica;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
