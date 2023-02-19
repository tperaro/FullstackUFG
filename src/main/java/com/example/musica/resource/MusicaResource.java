package com.example.musica.resource;

import com.example.musica.Model.Musica;
import com.example.musica.resource.DTO.MusicaDTO;
import com.example.musica.resource.DTO.MusicaResourceExceptionDTO;
import com.example.musica.service.MusicaService;
import com.example.musica.service.exceptions.MusicNotFoundException;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*")
@RestController()
@ApiResponses(value = {
    @ApiResponse(code = 400, message = "", response = MusicaResourceExceptionDTO.class),
    @ApiResponse(code = 404, message = "", response = MusicaResourceExceptionDTO.class),
    @ApiResponse(code = 500, message = "", response = MusicaResourceExceptionDTO.class)
})
public class MusicaResource {

    @Autowired
    MusicaService musicaService;

    @GetMapping(path = "/musicas")
    public List<MusicaDTO> findAll() {
        List<MusicaDTO> musicsDto = new ArrayList<>();
        List<Musica> foundMusics = musicaService.findAll();
        for (Musica musica : foundMusics) {
            musicsDto.add(MusicaDTO.fromMusic(musica));
        }
        return musicsDto;
    }

    @PostMapping(path = "/musicas", consumes = "multipart/form-data")
    @ResponseStatus(code = HttpStatus.CREATED)
    public MusicaDTO createMusic(
        @NotNull @RequestPart(value = "music", required = true, name = "music") MultipartFile musicFile,
        @NotNull @RequestPart(value = "thumbnail", required = true, name = "thumbnail") MultipartFile thumbnailFile,
        @NotNull @RequestPart(value = "name", required = true, name = "name") String name,
        @NotNull @RequestPart(value = "description", required = true, name = "description") String description,
        @NotNull @RequestPart(value = "genre", required = true, name = "genre") String genre
    ) throws Exception{
        Musica music = musicaService.create(name, description, genre, thumbnailFile, musicFile);
        return MusicaDTO.fromMusic(music);
    }

    @PutMapping(path = "/musicas/{id}", consumes = "multipart/form-data")
    @ResponseStatus(code = HttpStatus.OK)
    public MusicaDTO updateMusic(
        @PathVariable Long id,
        @Nullable @RequestPart(value = "music", required = false) MultipartFile musicFile,
        @Nullable @RequestPart(value = "thumbnail", required = false) MultipartFile thumbnailFile,
        @Nullable @RequestPart(value = "name", required = false) String name,
        @Nullable @RequestPart(value = "description", required = false) String description,
        @Nullable @RequestPart(value = "genre", required = false) String genre
    ) throws Exception, MusicNotFoundException, RuntimeException, IllegalArgumentException{
        Musica music = musicaService.update(id, name, description, genre, thumbnailFile, musicFile);
        return MusicaDTO.fromMusic(music);
    }

    @DeleteMapping(path = "/musicas/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        musicaService.deleteById(id);
    }

    @GetMapping("/musicas/arquivo/{name}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<String> getFile(HttpServletResponse response, String url, @PathVariable String name) throws Exception {
        response.sendRedirect("/" + name);
        return null;
    }
}
