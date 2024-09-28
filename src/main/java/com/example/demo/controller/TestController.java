package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    
    @GetMapping("/")
    public String getHome() {
        return "index";
    }

    @GetMapping("/test")
    public String getTest() {
        return "test";
    }
    
}
