package com.example.musica.resource;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.musica.resource.DTO.MusicaResourceExceptionDTO;
import com.example.musica.service.exceptions.MusicNotFoundException;
import com.example.musica.service.exceptions.StorageServiceException;

@RestControllerAdvice
public class MusicaResourceErrorHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<MusicaResourceExceptionDTO> handleExceptions(Exception exception, WebRequest webRequest) {
    if (exception instanceof MusicNotFoundException) {
      return new ResponseEntity<>(new MusicaResourceExceptionDTO("music-not-found", "Musica não encontrada"),
          HttpStatus.NOT_FOUND);
    }
    if (exception instanceof StorageServiceException) {
      return new ResponseEntity<>(
          new MusicaResourceExceptionDTO("music-storage-error", "Ocorreu um erro no armazenamento da música"),
          HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(
        new MusicaResourceExceptionDTO("internal-server-error", "Ocorreu um erro interno no sistema"),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    return new ResponseEntity<>(
        new MusicaResourceExceptionDTO("media-not-supported", "Certifique-se de estar enviado o arquivo corretamente"),
        HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    return new ResponseEntity<>(
        new MusicaResourceExceptionDTO("media-not-acceptable", "Certifique-se de estar enviado o arquivo corretamente"),
        HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    return new ResponseEntity<>(
        new MusicaResourceExceptionDTO("type-mismatch", "Um dos argumentos enviados está com o tipo errado"),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({ ConstraintViolationException.class })
  public ResponseEntity<Object> handleConstraintViolation(
      ConstraintViolationException ex, WebRequest request) {
    return new ResponseEntity<>(
        new MusicaResourceExceptionDTO("type-mismatch", "Um dos campos enviados violou uma restrição"),
        HttpStatus.BAD_REQUEST);
  }

}
