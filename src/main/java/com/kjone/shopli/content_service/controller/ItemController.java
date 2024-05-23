package com.kjone.shopli.content_service.controller;


import com.kjone.shopli.content_service.domain.entity.Item;
import com.kjone.shopli.content_service.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    
    @GetMapping("/all")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/getId")
    public ResponseEntity<Item> getItemById(@RequestParam Long id) {
        Item item = itemService.getItemById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping("/create/item")
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        Item createdItem = itemService.createItem(item);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item itemDetails) {
        Item updatedItem = itemService.updateItem(id, itemDetails);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Item>> searchItems(@RequestParam String keyword) {
        List<Item> items = itemService.searchItems(keyword);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}