package com.kjone.shopli.content_service.service.impl;

import com.kjone.shopli.content_service.domain.entity.CartItem;
import com.kjone.shopli.content_service.domain.entity.Item;
import com.kjone.shopli.content_service.repository.CartItemRepository;
import com.kjone.shopli.content_service.service.CartItemService;
import com.kjone.shopli.user_service.domain.user.User;
//import com.kjone.shopli.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
//    private final UserService userService;

    @Override
    public List<CartItem> getCartItemsByUserId(Long userId) {
        return cartItemRepository.findByUserId(userId);
    }

    @Override
    public CartItem addCartItem(User user, Item item, int quantity) {
        CartItem cartItem = CartItem.builder()
                .user(user)
                .item(item)
                .quantity(quantity)
                .build();
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void removeCartItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }
}
