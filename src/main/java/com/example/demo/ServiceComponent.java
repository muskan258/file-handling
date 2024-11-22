package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ServiceComponent {

    private final String UPLOAD_DIR = "uploads/";

    // Constructor to create the upload directory if it does not exist
    @Autowired
    public ServiceComponent() {
        try {
            Files.createDirectories(Paths.get(UPLOAD_DIR));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to save the uploaded file
    public String saveFile(MultipartFile file) throws IOException {
        Path filePath = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
        Files.write(filePath, file.getBytes());
        return file.getOriginalFilename();
    }

    // Method to delete a file
    public boolean deleteFile(String fileName) throws IOException {
        Path filePath = Paths.get(UPLOAD_DIR + fileName);
        if (Files.exists(filePath)) {
            Files.delete(filePath);
            return true;
        }
        return false;
    }

    // Method to check if a file exists
    public boolean fileExists(String fileName) {
        Path filePath = Paths.get(UPLOAD_DIR + fileName);
        return Files.exists(filePath);
    }

    // Add more methods for file handling if needed
}
