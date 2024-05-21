package com.kjone.shopli.content_service.service;


import com.kjone.shopli.content_service.domain.content.Content;

public interface ContentService {

    public boolean createContent(ContentRequest contentRequest);

    public ContentResponse getContentById(Long id);

    public ContentResponse updateContent(ContentRequest contentRequest);
}
