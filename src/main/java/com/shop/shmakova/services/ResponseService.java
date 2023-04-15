package com.shop.shmakova.services;

import com.shop.shmakova.models.Product;
import com.shop.shmakova.models.Response;
import com.shop.shmakova.models.User;
import com.shop.shmakova.repositories.ResponseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sh1chiro 01.04.2023
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ResponseService {
    private final ResponseRepository responseRepository;
    public void saveResponse(Response response, Product product, User user){
        response.setProduct(product);
        response.setUser(user);
        responseRepository.save(response);
    }
    public void deleteById(Long id){
        responseRepository.deleteById(id);
    }
    public List<Response> getByProductId(Long product_id){
        return responseRepository.findByProductId(product_id);
    }
}
