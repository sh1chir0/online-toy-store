package com.shop.shmakova.services;

import com.shop.shmakova.models.Blog;
import com.shop.shmakova.models.BlogImage;
import com.shop.shmakova.repositories.BlogImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author sh1chiro 04.04.2023
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BlogImageService {
    private final BlogImageRepository blogImageRepository;
    private final BlogService blogService;
    public void create(MultipartFile file, Long post_id) throws IOException {
        blogImageRepository.save(toImageEntity(file, post_id));
        Blog blog = blogService.getBlogById(post_id);
        blog.setImageId(getImageByPostId(post_id).getId());
        blogService.update(blog);
    }
    private BlogImage toImageEntity(MultipartFile file, Long post_id) throws IOException{
        BlogImage image = new BlogImage();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        image.setPostId(post_id);
        return image;
    }
    public void remove(Long id){
        blogImageRepository.deleteById(id);
    }
    private BlogImage getImageByPostId(Long id){return blogImageRepository.findByPostId(id);}
}
