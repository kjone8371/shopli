package com.kjone.shopli.content_service.controller;


import com.kjone.shopli.content_service.domain.entity.Item;
import com.kjone.shopli.content_service.service.ItemService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;



    // 아이템 전체 정보 가져오기
    @GetMapping("/all")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }



    // 해당 아이템 가져오기
    @GetMapping("/getId")
    public ResponseEntity<Item> getItemById(@RequestParam Long id) {
        Item item = itemService.getItemById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        return new ResponseEntity<>(item, HttpStatus.OK);
    }



    // 아이템 생성하기
    @PostMapping("/create/item")
    public ResponseEntity<Item> createItem(@RequestBody Item item, HttpServletRequest request) {
//        Item createdItem = itemService.createItem(item);
//        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
        // 여기서는 추가적인 권한 검증 로직을 넣을 수 있습니다.
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Item createdItem = itemService.createItem(item);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }



    // 해당 아이템 업데이트
    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item itemDetails) {
        Item updatedItem = itemService.updateItem(id, itemDetails);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

    // 해당 아이템 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 아이템 검색
    @GetMapping("/search")
    public ResponseEntity<List<Item>> searchItems(@RequestParam String keyword) {
        List<Item> items = itemService.searchItems(keyword);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}