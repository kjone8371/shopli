package com.kjone.shopli.content_service.domain.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Builder
@Table(name = "order_item")
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
ㅇ
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order_id;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item_id;

    private int quantity;
    private double price;
}
