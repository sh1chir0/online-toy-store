package com.shop.shmakova.repositories;

import com.shop.shmakova.models.MasterClassOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author sh1chiro 14.04.2023
 */
public interface MasterClassOrderRepository extends JpaRepository<MasterClassOrder, Long> {
}
