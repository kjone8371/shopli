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
    private Long myPost; // 내 게시물
    private List<CartItemResponse> cartItems;

    public static ProfileResponse from(Profile profile, List<CartItemResponse> cartItemResponses) {
        return ProfileResponse.builder()
                .id(profile.getId())
                .nickname(profile.getNickname())
                .image(profile.getImage())
                .phone(profile.getPhone())
                .cartItems(cartItemResponses)
                .build();
    }
}
