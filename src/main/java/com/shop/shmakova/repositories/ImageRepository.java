package com.shop.shmakova.repositories;

import com.shop.shmakova.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author sh1chiro 18.03.2023
 */
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByProductId(Long product_id);
}
