package com.shop.shmakova.repositories;

import com.shop.shmakova.models.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author sh1chiro 03.04.2023
 */
public interface UserLogRepository extends JpaRepository<UserLog, Long> {
}
