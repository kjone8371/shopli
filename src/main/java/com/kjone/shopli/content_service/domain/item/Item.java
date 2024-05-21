package com.kjone.shopli.content_service.domain.item;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer price;
    private String itemDetail;
    private String sellStatCd;

    @CreationTimestamp
    private LocalDateTime createTime = LocalDateTime.now();;

    @UpdateTimestamp
    private LocalDateTime updateTime = LocalDateTime.now();;
}
