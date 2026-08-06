package com.info.demo.controller;

import com.info.demo.entity.Order;
import com.info.demo.entity.User;
import com.info.demo.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> save(@RequestBody Order order){
        return ResponseEntity.ok(orderService.save(order));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id){
        return ResponseEntity.ok(orderService.findById(id));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Order>> findAll(){
        return ResponseEntity.ok(orderService.findAll());
    }
}
