package com.kjone.shopli.content_service.domain.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "item_entity")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; //상품코드

    @Column(nullable = false, length = 50)
    private String name; //상품명

    @Column(nullable = false)
    private int price; //가격

    @Column(nullable= false)
    private int stockNumber; //재고수량

    @Lob
    @Column(nullable = false)
    private String itemDetail; //상품 상세 설명

//    @Enumerated(EnumType.STRING)
//    private ItemSellStatus itemSellStatus; //상품 판매 상태

}