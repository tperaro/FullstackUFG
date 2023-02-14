package com.example.blog.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "comentarios")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long id;

    @Column(nullable = false, name = "NUMR_autor")
    private String autor;

    @Column(nullable = false, name = "DESC_Conteudo")
    @Size(min = 50, max = 999, message = "Um comentário deve ter entre 50 e 999 caracteres")
    private String conteudo;

    @Column(name = "NUMG_NumeroDeLikes")
    private Integer numerodelikes;

    @Column(nullable = false, name = "postid")
    private Long postId;

    public Comentario() {
    }

    public Comentario(Long id, String autor, String conteudo, Integer numerodelikes) {
        this.id = id;
        this.autor = autor;
        this.conteudo = conteudo;
        this.numerodelikes = numerodelikes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAutor(String autor) {
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

    public String getAutor() {
        return autor;
    }

    public String getConteudo() {
        return conteudo;
    }

    public Integer getNumerodelikes() {
        return numerodelikes;
    }

    public void daUmLikeNoComentario() {
        this.numerodelikes++;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

}
