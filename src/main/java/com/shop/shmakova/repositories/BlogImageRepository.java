package com.shop.shmakova.repositories;

import com.shop.shmakova.models.BlogImage;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author sh1chiro 04.04.2023
 */
public interface BlogImageRepository extends JpaRepository<BlogImage, Long> {
    BlogImage findByPostId(Long post_id);
}