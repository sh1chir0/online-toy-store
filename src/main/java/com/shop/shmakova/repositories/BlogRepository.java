package com.shop.shmakova.repositories;

import com.shop.shmakova.models.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author sh1chiro 04.04.2023
 */
public interface BlogRepository extends JpaRepository<Blog, Long> {
    Blog findByTitle(String title);
}
