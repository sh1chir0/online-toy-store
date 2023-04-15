package com.shop.shmakova.services;

import com.shop.shmakova.models.ProductLog;
import com.shop.shmakova.repositories.ProductLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sh1chiro 03.04.2023
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ProductLogService {
    private final ProductLogRepository productLogRepository;
    public void log(String log){
        ProductLog productLog = new ProductLog();
        productLog.setLog(log);
        productLogRepository.save(productLog);
    }
    public List<ProductLog> all(){
        return productLogRepository.findAll();
    }
}
