package com.umang.imageservice1.controller;

import com.umang.imageservice1.model.Image;
import com.umang.imageservice1.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @PostMapping
    public ResponseEntity<Image> createImage(@RequestBody Image image) {
        Image savedImage = imageRepository.save(image);
        return ResponseEntity.ok(savedImage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Image> getImage(@PathVariable Long id) {
        return imageRepository.findById(id)
                .map(image -> ResponseEntity.ok(image))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Image> updateImage(@PathVariable Long id, @RequestBody Image image) {
        if (!imageRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        image.setId(id);
        Image updatedImage = imageRepository.save(image);
        return ResponseEntity.ok(updatedImage);
    }




}
