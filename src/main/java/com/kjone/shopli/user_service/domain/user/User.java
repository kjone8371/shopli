package com.kjone.shopli.user_service.domain.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kjone.shopli.user_service.domain.role.Authority;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_entity")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private String email;
    private String password;
    private String username; // 유저 이름
    private int age; // 나이
    @JsonIgnore
    private Long image;

    @CreationTimestamp // INSERT 시 자동으로 값을 채워줌
    private LocalDateTime createTime = LocalDateTime.now();

    @UpdateTimestamp // UPDATE 시 자동으로 값을 채워줌
    private LocalDateTime updateTime = LocalDateTime.now();

    // 권한이라는 것에 외래키를 추가 함으로써
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "authority", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "roles")
    private Set<Authority> roles = new HashSet<>();


    public User(Long id, String email, String password, String username, int age, LocalDateTime createTime, LocalDateTime updateTime, Set<Authority> roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.age = age;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.roles = roles;
    }
}
