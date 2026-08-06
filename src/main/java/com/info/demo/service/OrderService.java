package com.info.demo.service;

import com.info.demo.entity.Order;
import com.info.demo.entity.User;
import com.info.demo.repository.OrderRepository;
import com.info.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order save(Order order){
        return orderRepository.save(order);
    }
    public Order findById(Long id){
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

}
