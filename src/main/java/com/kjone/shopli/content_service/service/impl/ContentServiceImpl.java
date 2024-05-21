package com.kjone.shopli.content_service.service.impl;

import com.kjone.shopli.content_service.repository.ContentRepository;
import com.kjone.shopli.content_service.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {
    private final ContentRepository contentRepository;


    @Override
    public boolean createContent(ContentRequest contentRequest) {
        return false;
    }

    @Override
    public ContentResponse getContentById(Long id) {

        return null;
    }

    @Override
    public ContentResponse updateContent(ContentRequest contentRequest) {
        return null;
    }
}
