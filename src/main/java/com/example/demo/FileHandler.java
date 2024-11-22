package com.example.demo;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

@RestController
@RequestMapping("/files")
public class FileHandler {

    private static final String UPLOAD_DIR = "uploads/";

    // Constructor to create the directory if it does not exist
    public FileHandler() {
        try {
            Files.createDirectories(Paths.get(UPLOAD_DIR));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Upload a file
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            Path filePath = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
            Files.write(filePath, file.getBytes());
            return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("File upload failed: " + e.getMessage());
        }
    }

    // View or download a file
    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewFile(@PathVariable String fileName) {
        try {
            Path filePath = Paths.get(UPLOAD_DIR, fileName);
            if (Files.exists(filePath)) {
                Resource resource = new UrlResource(filePath.toUri());
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // List all files in the upload directory
    @GetMapping("/list")
    public ResponseEntity<Stream<Path>> listFiles() {
        try {
            Stream<Path> fileList = Files.walk(Paths.get(UPLOAD_DIR), 1)
                    .filter(path -> !path.equals(Paths.get(UPLOAD_DIR)))
                    .map(Paths.get(UPLOAD_DIR)::relativize);
            return ResponseEntity.ok(fileList);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Delete a file
    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        try {
            Path filePath = Paths.get(UPLOAD_DIR, fileName);
            if (Files.exists(filePath)) {
                Files.delete(filePath);
                return ResponseEntity.ok("File deleted successfully: " + fileName);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found: " + fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete file: " + e.getMessage());
        }
    }
}
