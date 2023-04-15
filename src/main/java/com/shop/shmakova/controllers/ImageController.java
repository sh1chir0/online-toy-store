package com.shop.shmakova.controllers;

import com.shop.shmakova.models.BlogImage;
import com.shop.shmakova.models.Image;
import com.shop.shmakova.models.MasterClassImage;
import com.shop.shmakova.repositories.BlogImageRepository;
import com.shop.shmakova.repositories.ImageRepository;
import com.shop.shmakova.repositories.MasterClassImageRepository;
import com.shop.shmakova.repositories.MasterClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

/**
 * @author sh1chiro 18.03.2023
 */
@RestController
@RequiredArgsConstructor
public class ImageController {
    private final ImageRepository imageRepository;
    private final BlogImageRepository blogImageRepository;
    private final MasterClassImageRepository masterClassImageRepository;

    @GetMapping("/images/{id}")
    private ResponseEntity<?> getImageById(@PathVariable Long id){
        Image image = imageRepository.findById(id).orElse(null);
        return ResponseEntity.ok()
                .header("fileName", image.getOriginalFileName())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }
    @GetMapping("/blog-images/{id}")
    private ResponseEntity<?> getBlogImageById(@PathVariable Long id){
        BlogImage image = blogImageRepository.findById(id).orElse(null);
        return ResponseEntity.ok()
                .header("fileName", image.getOriginalFileName())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }
    @GetMapping("/master-class-images/{id}")
    private ResponseEntity<?> getMasterClassImageById(@PathVariable Long id){
        MasterClassImage image = masterClassImageRepository.findById(id).orElse(null);
        return ResponseEntity.ok()
                .header("fileName", image.getOriginalFileName())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }
}