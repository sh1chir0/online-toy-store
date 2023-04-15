package com.shop.shmakova.repositories;

import com.shop.shmakova.models.ProductLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author sh1chiro 03.04.2023
 */
public interface ProductLogRepository extends JpaRepository<ProductLog, Long> {
}
