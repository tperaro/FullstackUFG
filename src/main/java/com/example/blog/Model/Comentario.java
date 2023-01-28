package com.example.blog.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "comentarios")

public class Comentario {
    public Comentario() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAutor(Long autor) {
        this.autor = autor;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public void setNumerodelikes(Integer numerodelikes) {
        this.numerodelikes = numerodelikes;
    }

    public Long getId() {
        return id;
    }

    public Long getAutor() {
        return autor;
    }

    public String getConteudo() {
        return conteudo;
    }

    public Integer getNumerodelikes() {
        return numerodelikes;
    }

    public Comentario(Long id, Long autor, String conteudo, Integer numerodelikes) {
        this.id = id;
        this.autor = autor;
        this.conteudo = conteudo;
        this.numerodelikes = numerodelikes;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NUMG_idDoObjeto")
    private Long id;
    @Column(nullable = false,name = "NUMR_autor")
    private Long autor;
    @Column(nullable = false,name = "DESC_Conteudo")
    private String conteudo;
    @Column(name = "NUMG_NumeroDeLikes")
    private Integer numerodelikes;

}
