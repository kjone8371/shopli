package com.kjone.shopli.user_service.domain.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_profile")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    @JsonIgnore
    @Column(length = 128)
    private Long image; // 이미지
    private int phone; // 전화번호
    private Long my_post; // 내 게시글

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


}
