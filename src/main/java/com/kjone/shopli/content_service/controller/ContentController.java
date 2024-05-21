package com.kjone.shopli.content_service.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/content")
public class ContentController {



    @GetMapping("/list/all")
    public ResponseEntity<List<?>> getAllContent(){

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
