package com.example.blog.resource;

import com.example.blog.Model.Comentario;
import com.example.blog.service.ComentarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/comentario")
public class ComentarioResource {
    @Autowired
    private ComentarioService comentarioService;

    @GetMapping("/{id}")
    public ResponseEntity<Comentario> buscarComentarioPorId(@PathVariable Long id){
        Comentario comentario = comentarioService.consultarComentarioPorId(id);
        return ResponseEntity.ok(comentario);
    }

    @PutMapping()
    public  Comentario alterarComentario(@Valid @RequestBody Comentario comentario){return comentarioService.salvarComentario(comentario);}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comentario criarComentario(@Valid @RequestBody Comentario comentario){return comentarioService.salvarComentario(comentario);}

    @PatchMapping("{id}")
    public Comentario daUmLike(@PathVariable Long id ){return comentarioService.likeComentarioPorId(id);}

    @DeleteMapping
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletaComentario(@PathVariable Long id){comentarioService.deletaComentarioPorId(id);}



}
