package com.jaykumar.imageservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image getImage(String id) {
        return imageRepository.findById(id).get();
    }

    public Image saveImage(Image image) {
        return imageRepository.save(image);
    }

    public void deleteImage(String id) {
        imageRepository.deleteById(id);
    }
}
