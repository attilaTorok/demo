package com.example.demo.palindrome.integration;

import com.example.demo.palindrome.controller.PalindromeController;
import com.example.demo.palindrome.model.PalindromeResult;
import com.example.demo.palindrome.service.PalindromeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(PalindromeController.class)
public class PalindromeControllerTest {
    
    @TestConfiguration
    static class PalindromeServiceTestContextConfiguration {
        
        @Bean
        public PalindromeService palindromeService() {
            return new PalindromeService();
        }
    }
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private PalindromeService service;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    private static final String URI = "/palindrome/";
    
    @Test
    public void testWhenOneCharacterPassedThenIsPalindrome() throws Exception {
        String palindrome = "x";
        String expected = objectMapper.writeValueAsString(new PalindromeResult(true));
        String isPalindrome = sendRequest(palindrome).getContentAsString();
        
        Assert.assertEquals(getMessage(palindrome, expected, isPalindrome), expected, isPalindrome);
    }
    
    @Test
    public void testWhenDatePassedThenIsPalindrome() throws Exception {
        String palindrome = "10/02/2001";
        String expected = objectMapper.writeValueAsString(new PalindromeResult(true));
        String isPalindrome = sendRequest(palindrome).getContentAsString();
        
        Assert.assertEquals(getMessage(palindrome, expected, isPalindrome), expected, isPalindrome);
        
        palindrome = "11/11/2002 11:11";
        isPalindrome = sendRequest(palindrome).getContentAsString();
        
        Assert.assertEquals(getMessage(palindrome, expected, isPalindrome), expected, isPalindrome);
    }
    
    @Test
    public void testWhenNotPalindromePassedThanNotPalindrome() throws Exception {
        String palindrome = "This is not a palindrom.";
        String expected = objectMapper.writeValueAsString(new PalindromeResult(false));
        String isPalindrome = sendRequest(palindrome).getContentAsString();
        
        Assert.assertEquals(getMessage(palindrome, expected, isPalindrome), expected, isPalindrome);
    }
    
    @Test
    public void testWhenPalindromePassedThenIsPalindrome() throws Exception {
        String palindrome = "In girum imus nocte et consumimur igni";
        String expected = objectMapper.writeValueAsString(new PalindromeResult(true));
        String isPalindrome = sendRequest(palindrome).getContentAsString();
        
        Assert.assertEquals(getMessage(palindrome, expected, isPalindrome), expected, isPalindrome);
    }
    
    @Test
    public void testWhenNumberPalindromePassedThenIsPalindrome() throws Exception {
        String palindrome = "1221";
        String expected = objectMapper.writeValueAsString(new PalindromeResult(true));
        String isPalindrome = sendRequest(palindrome).getContentAsString();
        
        Assert.assertEquals(getMessage(palindrome, expected, isPalindrome), expected, isPalindrome);
    }
    
    @Test
    public void testWhenOddLengthPalindromePassedThenIsPalindrome() throws Exception {
        String palindrome = "12321";
        String expected = objectMapper.writeValueAsString(new PalindromeResult(true));
        String isPalindrome = sendRequest(palindrome).getContentAsString();
        
        Assert.assertEquals(getMessage(palindrome, expected, isPalindrome), expected, isPalindrome);
    }
    
    @Test
    public void testWhenEvenLengthPalindromePassedThenIsPalindrome() throws Exception {
        String palindrome = "1221";
        String expected = objectMapper.writeValueAsString(new PalindromeResult(true));
        String isPalindrome = sendRequest(palindrome).getContentAsString();
        
        Assert.assertEquals(getMessage(palindrome, expected, isPalindrome), expected, isPalindrome);
    }
    
    private MockHttpServletResponse sendRequest(String palindrome) throws Exception {
        return this.mockMvc.perform(post(URI)
                .content(palindrome)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();
    }
    
    private static String getMessage(String input, String expected, String result) {
        return input == null ? "Null" : input + " should return " + expected + " and not " + result;
    }
    
}
