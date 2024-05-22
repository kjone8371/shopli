package com.kjone.shopli.content_service.repository;

import com.kjone.shopli.content_service.domain.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByNameContaining(String name);

}
