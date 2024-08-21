package com.jaykumar.imageservice;

import io.minio.*;
import io.minio.messages.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("storage")
public class ImageController {

    private final MinioClient minioClient;
    private final ImageService imageService;

    @Value("${MINIO_BUCKET}")
    private String bucket;

    @Autowired
    public ImageController(MinioClient minioClient, ImageService imageService) {
        this.minioClient = minioClient;
        this.imageService = imageService;
    }

    @GetMapping("/buckets")
    public List<String> getBuckets() {
        try {
            System.out.println(this.minioClient.listBuckets());
            return this.minioClient.listBuckets().stream().map((Bucket::name)).toList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());

            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
            } else {
                System.out.println("Bucket already exists.");
            }

            UUID uuid = UUID.randomUUID();

            minioClient.putObject(
                    PutObjectArgs.builder().bucket(bucket)
                            .stream(file.getInputStream(),file.getSize(), -1)
                            .object(uuid.toString())
                            .contentType(file.getContentType())
                            .build());

            imageService.saveImage(new Image(uuid.toString(), file.getOriginalFilename(), file.getContentType()));

            return "File uploaded successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "File upload failed: " + e.getMessage();
        }
    }

}