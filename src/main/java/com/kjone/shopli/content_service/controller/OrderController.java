package com.kjone.shopli.content_service.controller;

import com.kjone.shopli.content_service.domain.entity.Order;
import com.kjone.shopli.content_service.domain.entity.OrderItem;
import com.kjone.shopli.content_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/{userId}")
    public ResponseEntity<Order> placeOrder(@PathVariable Long userId, @RequestBody List<OrderItem> orderItems) {
        Order order = orderService.placeOrder(userId, orderItems);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable Long userId) {
        List<Order> orders = orderService.getOrdersByUserId(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
