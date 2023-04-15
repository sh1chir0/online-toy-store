package com.shop.shmakova.repositories;

import com.shop.shmakova.models.Response;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author sh1chiro 01.04.2023
 */
public interface ResponseRepository extends JpaRepository<Response, Long> {
    List<Response> findByProductId(Long product_id);
}
