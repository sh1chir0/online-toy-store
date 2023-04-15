package com.shop.shmakova.services;

import com.shop.shmakova.models.Image;
import com.shop.shmakova.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author sh1chiro 30.03.2023
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    private final ProductService productService;

    public void add(MultipartFile file, Long product_id) throws IOException {
        imageRepository.save(toImageEntity(file,product_id));
    }
    private Image toImageEntity(MultipartFile file, Long product_id) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        image.setProduct(productService.getProductById(product_id));
        return image;
    }
    public List<Image> findByProductId(Long product_id){
        return imageRepository.findByProductId(product_id);
    }

    public void remove(Long id){
        imageRepository.deleteById(id);
    }

}
