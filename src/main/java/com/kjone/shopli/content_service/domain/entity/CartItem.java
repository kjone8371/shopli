package com.kjone.shopli.content_service.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kjone.shopli.user_service.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Table(name = "cart_item")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    private int quantity;


}
