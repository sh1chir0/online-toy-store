package com.shop.shmakova.repositories;

import com.shop.shmakova.models.Product;
import com.shop.shmakova.models.enums.TypeOfProducts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author sh1chiro 18.03.2023
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByTitle(String title);
    List<Product> findByType(TypeOfProducts typeOfProducts);
}
