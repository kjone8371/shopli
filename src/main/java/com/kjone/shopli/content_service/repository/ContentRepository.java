package com.kjone.shopli.content_service.repository;

import com.kjone.shopli.content_service.domain.content.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {

    Optional<Content> findByContName(String cont_name);

    Optional<Content> findByTitle(String title);

}
