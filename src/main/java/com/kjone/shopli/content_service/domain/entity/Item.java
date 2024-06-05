package com.kjone.shopli.content_service.domain.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

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
    private String name; //상품

    @Column(nullable = false)
    private String price; //가격 // 수정

    @Column(nullable= false)
    private int stockNumber; //재고수량

    private Long image;

    @Lob
    @Column(nullable = false)
    private String itemDetail; //상품 상세 설명


    @CreationTimestamp
    private LocalDateTime createTime = LocalDateTime.now();  // 생성 날짜

    @UpdateTimestamp
    private LocalDateTime updateTime = LocalDateTime.now(); // 업데이트 날짜

//    @Enumerated(EnumType.STRING)
//    private ItemSellStatus itemSellStatus; //상품 판매 상태

}