package com.example.demo.palindrome.unit;

import com.example.demo.palindrome.controller.PalindromeController;
import com.example.demo.palindrome.model.PalindromeResult;
import com.example.demo.palindrome.service.PalindromeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * The unit tests for {@link PalindromeController}.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PalindromeController.class)
public class PalindromeControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private PalindromeService service;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    private static final String URI = "/palindrome/";
    
    @Test
    public void testWhenOneCharacterPassedThenIsPalindrome() throws Exception {
        String palindrome = "x";
        PalindromeResult serviceReturn = new PalindromeResult(true);
        String expected = objectMapper.writeValueAsString(serviceReturn);
        when(service.isPalindrome(palindrome)).thenReturn(serviceReturn);
        String isPalindrome = sendRequest(palindrome).getContentAsString();
    
        Assert.assertEquals(getMessage(palindrome, expected, isPalindrome), expected, isPalindrome);
        verify(service).isPalindrome(palindrome);
    }
    
    @Test
    public void testWhenDatePassedThenIsPalindrome() throws Exception {
        String palindrome = "10/02/2001";
        PalindromeResult serviceReturn = new PalindromeResult(true);
        String expected = objectMapper.writeValueAsString(serviceReturn);
        when(service.isPalindrome(palindrome)).thenReturn(serviceReturn);
        String isPalindrome = sendRequest(palindrome).getContentAsString();
    
        Assert.assertEquals(getMessage(palindrome, expected, isPalindrome), expected, isPalindrome);
        verify(service).isPalindrome(palindrome);
        
        palindrome = "11/11/2002 11:11";
        serviceReturn = new PalindromeResult(true);
        expected = objectMapper.writeValueAsString(serviceReturn);
        when(service.isPalindrome(palindrome)).thenReturn(serviceReturn);
        isPalindrome = sendRequest(palindrome).getContentAsString();
    
        Assert.assertEquals(getMessage(palindrome, expected, isPalindrome), expected, isPalindrome);
        verify(service).isPalindrome(palindrome);
    }
    
    @Test
    public void testWhenNotPalindromePassedThanNotPalindrome() throws Exception {
        String palindrome = "This is not a palindrom.";
        PalindromeResult serviceReturn = new PalindromeResult(false);
        String expected = objectMapper.writeValueAsString(serviceReturn);
        when(service.isPalindrome(palindrome)).thenReturn(serviceReturn);
        String isPalindrome = sendRequest(palindrome).getContentAsString();
    
        Assert.assertEquals(getMessage(palindrome, expected, isPalindrome), expected, isPalindrome);
        verify(service).isPalindrome(palindrome);
    }
    
    @Test
    public void testWhenPalindromePassedThenIsPalindrome() throws Exception {
        String palindrome = "In girum imus nocte et consumimur igni";
        PalindromeResult serviceReturn = new PalindromeResult(true);
        String expected = objectMapper.writeValueAsString(serviceReturn);
        when(service.isPalindrome(palindrome)).thenReturn(serviceReturn);
        String isPalindrome = sendRequest(palindrome).getContentAsString();
    
        Assert.assertEquals(getMessage(palindrome, expected, isPalindrome), expected, isPalindrome);
        verify(service).isPalindrome(palindrome);
    }
    
    @Test
    public void testWhenNumberPalindromePassedThenIsPalindrome() throws Exception {
        String palindrome = "1221";
        PalindromeResult serviceReturn = new PalindromeResult(true);
        String expected = objectMapper.writeValueAsString(serviceReturn);
        when(service.isPalindrome(palindrome)).thenReturn(serviceReturn);
        String isPalindrome = sendRequest(palindrome).getContentAsString();
    
        Assert.assertEquals(getMessage(palindrome, expected, isPalindrome), expected, isPalindrome);
        verify(service).isPalindrome(palindrome);
    }
    
    @Test
    public void testWhenOddLengthPalindromePassedThenIsPalindrome() throws Exception {
        String palindrome = "12321";
        PalindromeResult serviceReturn = new PalindromeResult(true);
        String expected = objectMapper.writeValueAsString(serviceReturn);
        when(service.isPalindrome(palindrome)).thenReturn(serviceReturn);
        String isPalindrome = sendRequest(palindrome).getContentAsString();
    
        Assert.assertEquals(getMessage(palindrome, expected, isPalindrome), expected, isPalindrome);
        verify(service).isPalindrome(palindrome);
    }
    
    @Test
    public void testWhenEvenLengthPalindromePassedThenIsPalindrome() throws Exception {
        String palindrome = "1221";
        PalindromeResult serviceReturn = new PalindromeResult(true);
        String expected = objectMapper.writeValueAsString(serviceReturn);
        when(service.isPalindrome(palindrome)).thenReturn(serviceReturn);
        String isPalindrome = sendRequest(palindrome).getContentAsString();
    
        Assert.assertEquals(getMessage(palindrome, expected, isPalindrome), expected, isPalindrome);
        verify(service).isPalindrome(palindrome);
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
