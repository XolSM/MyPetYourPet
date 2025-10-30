package com.example.mypetyourpet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class testController {
    @GetMapping("/testing")
    public Map<String, String> testing() {
        return Map.of("status", "OK", "version", "v1");
    }
}
