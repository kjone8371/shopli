package com.kjone.shopli.content_service.domain.response;


import com.kjone.shopli.content_service.domain.entity.CartItem;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponse {
    private Long id;
    private int quantity;
    private Long itemId;
    private Long userId;

    public static CartItemResponse from(CartItem cartItem) {
        return new CartItemResponse(
                cartItem.getId(),
                cartItem.getQuantity(),
                cartItem.getItem().getId(),
                cartItem.getUser().getId()
        );
    }
}
