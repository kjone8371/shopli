package com.kjone.shopli.content_service.controller;

import com.kjone.shopli.content_service.domain.entity.CartItem;
import com.kjone.shopli.content_service.domain.entity.Item;
import com.kjone.shopli.content_service.service.CartItemService;
import com.kjone.shopli.content_service.service.ItemService;
import com.kjone.shopli.user_service.domain.user.User;
import com.kjone.shopli.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartItemService cartItemService;
    private final UserService userService;
    private final ItemService itemService;

    // 카트 유저 가져오기
    @GetMapping("/{userId}")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable Long userId) {
        List<CartItem> cartItems = cartItemService.getCartItemsByUserId(userId);
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }

    // 카트 아이템 등록
    @PostMapping("/{userId}/add")
    public ResponseEntity<CartItem> addCartItem(@PathVariable Long userId, @RequestParam Long itemId, @RequestParam int quantity) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Item item = itemService.getItemById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        CartItem cartItem = cartItemService.addCartItem(user, item, quantity);
        return new ResponseEntity<>(cartItem, HttpStatus.CREATED);
    }

    // 카트 아이템 삭제
    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<Void> removeCartItem(@PathVariable Long cartItemId) {
        cartItemService.removeCartItem(cartItemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}