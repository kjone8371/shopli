package com.kjone.shopli.content_service.service.impl;

import com.kjone.shopli.content_service.domain.entity.Order;
import com.kjone.shopli.content_service.domain.entity.OrderItem;
import com.kjone.shopli.content_service.repository.OrderRepository;
import com.kjone.shopli.content_service.service.OrderService;
import com.kjone.shopli.user_service.domain.user.User;
import com.kjone.shopli.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;

    @Override
    public Order placeOrder(Long userId, List<OrderItem> orderItems) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setOrderItems(orderItems);
        double totalPrice = orderItems.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
        order.setTotalPrice(totalPrice);

        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}
