package com.example.demo.smoke;

import com.example.demo.duplicate.controller.DuplicateController;
import com.example.demo.duplicate.service.DuplicateService;
import com.example.demo.fizzbuzz.controller.FizzBuzzController;
import com.example.demo.fizzbuzz.service.FizzBuzzService;
import com.example.demo.palindrome.controller.PalindromeController;
import com.example.demo.palindrome.service.PalindromeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for checking if every component is registered in the context.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SmokeTest {
    
    @Autowired
    private DuplicateController duplicateController;
    @Autowired
    private DuplicateService duplicateService;
    @Autowired
    private FizzBuzzController fizzBuzzController;
    @Autowired
    private FizzBuzzService fizzBuzzService;
    @Autowired
    private PalindromeController palindromeController;
    @Autowired
    private PalindromeService palindromeService;
    
    
    @Test
    public void contextLoads() throws Exception {
        assertThat(duplicateController).isNotNull();
        assertThat(duplicateService).isNotNull();
        assertThat(fizzBuzzController).isNotNull();
        assertThat(fizzBuzzService).isNotNull();
        assertThat(palindromeController).isNotNull();
        assertThat(palindromeService).isNotNull();
    }
}
