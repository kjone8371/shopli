package com.kjone.shopli.user_service.domain.request;

import lombok.Data;

@Data
public class ProfileRequest {
    private String nickname;
    private Long image; // 이미지
    private int phone; // 전화번호
    private Long my_post; // 내 게시물
}
