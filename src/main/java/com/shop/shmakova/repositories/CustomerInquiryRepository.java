package com.shop.shmakova.repositories;

import com.shop.shmakova.models.CustomerInquiry;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author sh1chiro 31.03.2023
 */
public interface CustomerInquiryRepository extends JpaRepository<CustomerInquiry, Long> {
    CustomerInquiry getInquiryById(Long id);
}
