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
    private String itemName;
    private int quantity;

    public static CartItemResponse from(CartItem cartItem) {
        return new CartItemResponse(
                cartItem.getId(),
                cartItem.getItem().getName(), // 예시로 Item에서 이름 가져오기
                cartItem.getQuantity()
        );
    }
}
