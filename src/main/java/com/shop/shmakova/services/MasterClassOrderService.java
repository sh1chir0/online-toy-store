package com.shop.shmakova.services;

import com.shop.shmakova.models.MasterClassOrder;
import com.shop.shmakova.models.Order;
import com.shop.shmakova.repositories.MasterClassOrderRepository;
import com.shop.shmakova.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sh1chiro 14.04.2023
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MasterClassOrderService {
    private final MasterClassOrderRepository orderRepository;

    public void saveOrder(MasterClassOrder order) {
        orderRepository.save(order);
    }

    public List<MasterClassOrder> all() {
        return orderRepository.findAll();
    }

    public void updateOrder(MasterClassOrder order) {
        orderRepository.save(order);
    }

    public MasterClassOrder getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
}