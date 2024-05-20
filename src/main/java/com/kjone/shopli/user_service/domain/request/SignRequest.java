package com.kjone.shopli.user_service.domain.request;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignRequest {
    private Long id; // 아이디
    private String email; // 이메일
    private String password; // 비밀번호
    private String username; // 유저 이름
    private int age; // 나이
    private Long image; // 이미지
}
