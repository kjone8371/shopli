package com.kjone.shopli.content_service.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kjone.shopli.user_service.domain.user.Profile;
import com.kjone.shopli.user_service.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @CreationTimestamp
    private LocalDateTime createTime = LocalDateTime.now();

    @UpdateTimestamp
    private LocalDateTime updateTime = LocalDateTime.now();

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;
}
