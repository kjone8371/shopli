package com.kjone.shopli.content_service.service.impl;

import com.kjone.shopli.content_service.domain.entity.Item;
import com.kjone.shopli.content_service.repository.ItemRepository;
import com.kjone.shopli.content_service.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(Long id, Item items) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        item.setName(items.getName());
        item.setPrice(items.getPrice());
        item.setStockNumber(items.getStockNumber());
        item.setImage(items.getImage());
        item.setItemDetail(items.getItemDetail());
        item.setUpdateTime(items.getUpdateTime());

        return itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        itemRepository.delete(item);
    }

    public List<Item> searchItems(String keyword) {
        return itemRepository.findByNameContaining(keyword);
    }
}
