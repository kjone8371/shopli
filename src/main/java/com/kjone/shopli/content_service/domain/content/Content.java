package com.kjone.shopli.content_service.domain.content;


import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "content_entity")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;




}
