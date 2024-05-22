package com.kjone.shopli.content_service.service;

import com.kjone.shopli.content_service.domain.entity.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    public List<Item> getAllItems();


    public Optional<Item> getItemById(Long id);


    public Item createItem(Item item);


    public Item updateItem(Long id, Item itemDetails);

    public void deleteItem(Long id);

    public List<Item> searchItems(String keyword);
}
ㅇ