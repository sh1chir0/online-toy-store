package com.shop.shmakova.repositories;

import com.shop.shmakova.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author sh1chiro 31.03.2023
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
