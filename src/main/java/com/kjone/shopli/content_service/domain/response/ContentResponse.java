package com.kjone.shopli.content_service.domain.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContentResponse {
    private Long id;
    private String title;
    private String cont_name;
    private String cont_link;


}
