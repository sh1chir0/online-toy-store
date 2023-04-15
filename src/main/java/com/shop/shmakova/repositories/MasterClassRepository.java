package com.shop.shmakova.repositories;

import com.shop.shmakova.models.MasterClass;
import com.shop.shmakova.models.Product;
import com.shop.shmakova.models.enums.TypeOfProducts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author sh1chiro 14.04.2023
 */
public interface MasterClassRepository extends JpaRepository<MasterClass, Long> {
    List<MasterClass> findByTitle(String title);
}
