package com.kjone.shopli.content_service.domain.request;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContentRequest {

    private Long id;
    private String title;
    private String cont_name;
    private String cont_link;

}
