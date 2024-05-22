package com.kjone.shopli.content_service.service;

import com.kjone.shopli.content_service.domain.entity.Order;
import com.kjone.shopli.content_service.domain.entity.OrderItem;

import java.util.List;

public interface OrderService {

    public Order placeOrder(Long userId, List<OrderItem> orderItems);

    public List<Order> getOrdersByUserId(Long userId);

}
ㅇ