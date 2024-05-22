package com.kjone.shopli.content_service.service;

import com.kjone.shopli.content_service.domain.entity.CartItem;
import com.kjone.shopli.content_service.domain.entity.Item;
import com.kjone.shopli.content_service.repository.CartItemRepository;
import com.kjone.shopli.user_service.domain.user.User;
import com.kjone.shopli.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;



public interface CartItemService {

    public List<CartItem> getCartItemsByUserId(Long userId);


    public CartItem addCartItem(User user, Item item, int quantity);


    public void removeCartItem(Long cartItemId);

}
