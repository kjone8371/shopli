package com.kjone.shopli.user_service.domain.response;


import com.kjone.shopli.content_service.domain.entity.CartItem;
import com.kjone.shopli.content_service.domain.response.CartItemResponse;
import com.kjone.shopli.user_service.domain.user.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponse {

    private Long id;
    private String nickname;
    private Long image; // 이미지
    private int phone; // 전화번호
    private Long my_post; // 내 게시물
    private List<CartItem> cartItems;

    public ProfileResponse(Long id, String nickname, int phone, Long myPost, List<CartItemResponse> cartItemDTOs) {
        this.id = id;
        this.nickname = nickname;
        this.phone = phone;
        this.my_post = myPost;
        this.cartItems = new ArrayList<>();
    }

    public static ProfileResponse from(Profile profile, List<CartItem> cartItems) {
        List<CartItemResponse> cartItemDTOs = cartItems.stream()
                .map(CartItemResponse::from)
                .toList();
        return new ProfileResponse(
                profile.getId(),
                profile.getNickname(),
                profile.getPhone(),
                profile.getMy_post(),
                cartItemDTOs
        );
    }
}
