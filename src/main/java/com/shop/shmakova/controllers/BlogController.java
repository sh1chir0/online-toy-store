package com.shop.shmakova.controllers;

import com.shop.shmakova.models.Blog;
import com.shop.shmakova.services.BlogImageService;
import com.shop.shmakova.services.BlogService;
import com.shop.shmakova.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Comparator;
import java.util.List;

/**
 * @author sh1chiro 04.04.2023
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/blog")
public class BlogController {
    private final BlogService blogService;
    private final BlogImageService blogImageService;
    private final UserService userService;
    @GetMapping("")
    public String blog(Model model, Principal principal) {
        List<Blog> posts = blogService.all();
        posts.sort(Comparator.comparing(Blog::getDateOfCreated).reversed());
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("posts", posts);
        return "blog";
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/new")
    public String newPost(){
        return "blog_create";
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/new")
    public String createPost(@RequestParam("file") MultipartFile file, Blog blog) throws IOException {
        blogService.create(blog);
        if(!file.isEmpty()){
            blogImageService.create(file, blogService.getByTitle(blog.getTitle()).getId());
        }
        return ("redirect:/blog");
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("post", blogService.getBlogById(id));
        return "blog_edit";
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/edit/{id}")
    public String editPost(@PathVariable Long id, @RequestParam("file") MultipartFile file, Blog blog) throws IOException {
        Blog blogAfterUpdate = blogService.getBlogById(id);
        blogAfterUpdate.setText(blog.getText());
        blogAfterUpdate.setTitle(blog.getTitle());
        blogService.update(blogAfterUpdate);

        if(!file.isEmpty()){
            if(blogAfterUpdate.getImageId() != 0){
                blogImageService.remove(blogAfterUpdate.getImageId());
                blogImageService.create(file, blogAfterUpdate.getId());
            }
            else{
                blogImageService.create(file,blogAfterUpdate.getId());
            }
        }
        return  ("redirect:/blog");
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id){
        blogService.delete(id);
        return ("redirect:/blog");
    }
}
