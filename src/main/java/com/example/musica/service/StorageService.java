package com.example.musica.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.musica.service.exceptions.StorageServiceException;

public interface StorageService {

    void init() throws StorageServiceException;

    String store(MultipartFile file) throws StorageServiceException;
    
    void delete(String fileName) throws StorageServiceException;
}