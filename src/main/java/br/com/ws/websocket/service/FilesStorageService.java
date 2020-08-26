package br.com.ws.websocket.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class FilesStorageService {

    private static  final String directory = System.getProperty("user.dir");

    public void save(MultipartFile file, String path) {
        try {
            Path pathImages = Paths.get(this.directory, path);
            Path pathFile = pathImages.resolve(Objects.requireNonNull(file.getOriginalFilename()));
            Files.createDirectories(pathImages);
            file.transferTo(pathFile.toFile());
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível mover arquivo: " + e.getMessage());
        }
    }

    public Resource get(Path path) {
        try {

            Path filePath = Paths.get(this.directory, path.toString());
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("Não foi possível achar arquivo: " + filePath.toString());
            }

        } catch (Exception e) {
            throw new RuntimeException("Não foi possível mover arquivo: " + e.getMessage());
        }
    }
}
