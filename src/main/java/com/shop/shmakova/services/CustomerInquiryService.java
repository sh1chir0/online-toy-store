package com.shop.shmakova.services;

import com.shop.shmakova.models.CustomerInquiry;
import com.shop.shmakova.repositories.CustomerInquiryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sh1chiro 31.03.2023
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerInquiryService {
    private final CustomerInquiryRepository customerInquiryRepository;

    public List<CustomerInquiry> all(){
        return customerInquiryRepository.findAll();
    }
    public void saveInquiry(CustomerInquiry customerInquiry){
        customerInquiryRepository.save(customerInquiry);
    }
    public CustomerInquiry getInquiryById(Long id){
        return customerInquiryRepository.getInquiryById(id);
    }
    public void update(CustomerInquiry customerInquiry){
        customerInquiryRepository.save(customerInquiry);
    }

}
