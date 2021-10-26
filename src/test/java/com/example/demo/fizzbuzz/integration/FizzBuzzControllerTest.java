package com.example.demo.fizzbuzz.integration;

import com.example.demo.fizzbuzz.controller.FizzBuzzController;
import com.example.demo.fizzbuzz.model.FizzBuzzResult;
import com.example.demo.fizzbuzz.service.FizzBuzzService;
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
@WebMvcTest(FizzBuzzController.class)
public class FizzBuzzControllerTest {
    
    @TestConfiguration
    static class FizzBuzzServiceTestContextConfiguration {
        
        @Bean
        public FizzBuzzService fizzBuzzService() {
            return new FizzBuzzService();
        }
    }
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private FizzBuzzService fizzBuzzService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    private static final String URI = "/fizzbuzz/";
    
    @Test
    public void testWhenZeroPassedThanNothingReturned() throws Exception {
        Long input = 0L;
        String expected = objectMapper.writeValueAsString(new FizzBuzzResult(""));
        String result = sendRequest(input).getContentAsString();
        
        Assert.assertEquals(getMessage(input, expected, result), expected, result);
    }
    
    @Test
    public void testWhenNullPassedThanNothingReturned() throws Exception {
        Long input = null;
        sendBadRequest(input);
    }
    
    @Test
    public void testWhenInputCanBeDividedBy3ReturnsFIZZ() throws Exception {
        long input = 3L;
        String expected = objectMapper.writeValueAsString(new FizzBuzzResult("FIZZ"));
        String result = sendRequest(input).getContentAsString();
        
        Assert.assertEquals(getMessage(input, expected, result), expected, result);
        
        input = 9L;
        result = sendRequest(input).getContentAsString();
        
        Assert.assertEquals(getMessage(input, expected, result), expected, result);
    }
    
    @Test
    public void testWhenInputCanBeDividedBy5ReturnsBUZZ() throws Exception {
        long input = 5L;
        String expected = objectMapper.writeValueAsString(new FizzBuzzResult("BUZZ"));
        String result = sendRequest(input).getContentAsString();
        
        Assert.assertEquals(getMessage(input, expected, result), expected, result);
        
        input = -50L;
        result = sendRequest(input).getContentAsString();
        
        Assert.assertEquals(getMessage(input, expected, result), expected, result);
    }
    
    @Test
    public void testWhenInputCanBeDividedBy3And5ReturnsFIZZBUZZ() throws Exception {
        long input = 15L;
        String expected = objectMapper.writeValueAsString(new FizzBuzzResult("FIZZBUZZ"));
        String result = sendRequest(input).getContentAsString();
        
        Assert.assertEquals(getMessage(input, expected, result), expected, result);
        
        input = -60L;
        result = sendRequest(input).getContentAsString();
        
        Assert.assertEquals(getMessage(input, expected, result), expected, result);
    }
    
    @Test
    public void testWhenInputCantBeDividedBy3Or5ReturnsNothing() throws Exception {
        long input = 7L;
        String expected = objectMapper.writeValueAsString(new FizzBuzzResult(""));
        String result = sendRequest(input).getContentAsString();
        
        Assert.assertEquals(getMessage(input, expected, result), expected, result);
        
        input = -1L;
        result = sendRequest(input).getContentAsString();
        
        Assert.assertEquals(getMessage(input, expected, result), expected, result);
    }
    
    private MockHttpServletResponse sendRequest(Long number) throws Exception {
        return this.mockMvc.perform(post(URI)
                .content(objectMapper.writeValueAsString(number))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();
    }
    
    private void sendBadRequest(Long number) throws Exception {
        this.mockMvc.perform(post(URI)
                .content(objectMapper.writeValueAsString(number))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
    
    private static String getMessage(Long input, String expected, String result) {
        return input == null ? "Null" : input + " should return " + expected + " and not " + result;
    }
    
}
