package com.example.blog.Model;

import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long id;

    @NotNull
    @Size(min = 5, max = 999, message = "Um post deve ter entre 5 e 999 caracteres")
    @Column(nullable = false, name = "TITULO")
    private String titulo;

    @NotNull
    @Size(min = 50, max = 999, message = "Um post deve ter entre 50 e 999 caracteres")
    @Column(nullable = false, name = "DESC_Conteudo")
    private String conteudo;

    @Column(name = "NUMG_NumeroDeLikes")
    private Integer numerodelikes;

    @CreationTimestamp
    @Column(name = "DATE_dataPublicacao")
    private Instant dataPublicacao;

    public Post(Long id, Integer numerodelikes, String conteudo, Instant dataPublicacao, String titulo) {
        this.id = id;
        this.numerodelikes = numerodelikes;
        this.conteudo = conteudo;
        this.dataPublicacao = dataPublicacao;
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public Integer getNumerodelikes() {
        return numerodelikes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumerodelikes(Integer numerodelikes) {
        this.numerodelikes = numerodelikes;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public void setDataPublicacao(Instant dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getConteudo() {
        return conteudo;
    }

    public Instant getDataPublicacao() {
        return dataPublicacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void daUmLike() {
        this.numerodelikes++;
    }

    public Post() {

    }
}
