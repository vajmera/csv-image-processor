package com.example.csvimage.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.csvimage.Service.ImageService;

@RestController
@RequestMapping("images")
public class ImageUpload {
    private ImageService imageService;
    @Autowired
    public ImageUpload(ImageService imageService){
        this.imageService=imageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCSVFile(@RequestParam("file") MultipartFile file) {
        if (!file.getOriginalFilename().endsWith(".csv")) {
            return ResponseEntity.badRequest().body("Upload csv files only.");
        }

        try {
            String requestId = imageService.processFile(file);
            return ResponseEntity.ok(requestId);
        } catch (Exception e) {
            e.printStackTrace(); // Log the error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the file.");
        }
    }
    @PostMapping("/download")
    public ResponseEntity<String>downloadCSVFile(@RequestParam("Id") String Id){
        System.out.println("HELLOWORLD");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Web socket ran succesfully");
    }
}


