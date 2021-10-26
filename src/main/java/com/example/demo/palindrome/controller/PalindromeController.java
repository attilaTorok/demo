package com.example.demo.palindrome.controller;

import com.example.demo.palindrome.model.PalindromeResult;
import com.example.demo.palindrome.service.PalindromeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/palindrome")
public class PalindromeController {
    
    @Autowired
    private PalindromeService service;
    
    @PostMapping("/")
    public PalindromeResult isPalindrome(@RequestBody String palindrome) {
        return service.isPalindrome(palindrome);
    }
    
}
