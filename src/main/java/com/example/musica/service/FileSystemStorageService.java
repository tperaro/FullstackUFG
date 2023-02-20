package com.example.musica.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.musica.MusicaApplication;
import com.example.musica.service.exceptions.StorageServiceException;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;

    public FileSystemStorageService() {
        this.rootLocation = MusicaApplication.getUploadPath();
    }

    @Override
    public String store(MultipartFile file) throws StorageServiceException {
        validate(file);
        String name = rename(file);
        saveToDestination(file, name);
        return name;
    }

    private void validate(MultipartFile file) throws StorageServiceException {
        final List<String> allowedMusicContentTypes = Arrays.asList("audio/mpeg", "audio/mpeg3", "image/png",
                "image/jpg", "image/jpeg");
        if (!allowedMusicContentTypes.contains(file.getContentType())) {
            throw new StorageServiceException();
        }
        if (file.isEmpty()) {
            throw new StorageServiceException();
        }
    }

    private String rename(MultipartFile file) throws StorageServiceException {
        String originalFileName = file.getOriginalFilename();
        if (originalFileName == null) {
            throw new StorageServiceException();
        }
        String[] splittedName = originalFileName.split("\\.");
        String extension = splittedName[splittedName.length - 1];
        String fileName = UUID.randomUUID().toString() + "." + extension;
        return fileName;
    }

    private void saveToDestination(MultipartFile file, String name) throws StorageServiceException {
        Path destinationFile = this.rootLocation.resolve(Paths.get(name))
                .normalize()
                .toAbsolutePath();
        if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
            throw new StorageServiceException();
        }
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, destinationFile,
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new StorageServiceException();
        }
    }

    @Override
	public void delete(String fileName) {
        try {
            Path file = load(fileName);
            FileSystemUtils.deleteRecursively(file);
        } catch(Exception e) {}
	}

	private Path load(String filename) {
		return rootLocation.resolve(filename);
	}

    @Override
    public void init() throws StorageServiceException {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageServiceException();
        }
    }
}