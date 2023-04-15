package com.shop.shmakova.services;

import com.shop.shmakova.models.Order;
import com.shop.shmakova.repositories.OrderRepository;
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
public class OrderService {
    private final OrderRepository orderRepository;
    public void saveOrder(Order order){
        orderRepository.save(order);
    }
    public List<Order> all(){
        return orderRepository.findAll();
    }
    public void updateOrder(Order order){orderRepository.save(order);}
    public Order getOrderById(Long id){
        return orderRepository.findById(id).orElse(null);
    }

}
