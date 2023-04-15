package com.shop.shmakova.services;

import com.shop.shmakova.models.Blog;
import com.shop.shmakova.repositories.BlogRepository;
import com.shop.shmakova.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sh1chiro 04.04.2023
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final ImageRepository imageRepository;
    public List<Blog> all(){
        return blogRepository.findAll();
    }
    public void create(Blog blog){
        blogRepository.save(blog);
    }
    public void update(Blog blog){
        blogRepository.save(blog);
    }
    public Blog getByTitle(String title){return blogRepository.findByTitle(title);}
    public Blog getBlogById(Long id){
        return blogRepository.findById(id).orElse(null);
    }
    public void delete(Long id){
        imageRepository.deleteById(getBlogById(id).getImageId());
        blogRepository.deleteById(id);
    }
}
