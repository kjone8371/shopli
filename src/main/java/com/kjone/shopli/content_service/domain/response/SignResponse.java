package com.kjone.shopli.content_service.domain.response;


import com.kjone.shopli.content_service.domain.role.Authority;
import com.kjone.shopli.content_service.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignResponse {
    private Long id;
    private String email;
    private String password;
    private String username; // 유저 이름
    private int age; // 나이
    private Long image;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Set<Authority> roles;

    public SignResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.username = user.getUsername();
        this.age = user.getAge();
        this.image = user.getImage();
        this.createTime = user.getCreateTime();
        this.updateTime = user.getUpdateTime();
        roles = user.getRoles();
    }
}
