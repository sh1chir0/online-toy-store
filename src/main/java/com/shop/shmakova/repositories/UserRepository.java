package com.shop.shmakova.repositories;

import com.shop.shmakova.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author sh1chiro 20.03.2023
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}