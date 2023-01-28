package com.example.blog.Model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "posts")
public class Post {
    public Post(Long id, Integer numerodelikes, String conteudo, Instant dataPublicacao) {
        this.id = id;
        this.numerodelikes = numerodelikes;
        this.conteudo = conteudo;
        this.dataPublicacao = dataPublicacao;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NUMG_idDoObjeto")
    private Long id;

    @Column(name = "NUMG_NumeroDeLikes")
    private Integer numerodelikes;

    @NotNull
    @Size(min = 50,max = 999,message = "Um post deve ter entre 50 e 999 caracteres")
    @Column(nullable = false,name = "DESC_Conteudo")
    private String conteudo;


    @CreationTimestamp
    @Column(name = "DATE_dataPublicacao")
    private Instant dataPublicacao;

    public void daUmLike(){
        this.numerodelikes++;
    }
    public Post() {

    }
}
