package com.example.demo.fizzbuzz.unit;

import com.example.demo.fizzbuzz.controller.FizzBuzzController;
import com.example.demo.fizzbuzz.model.FizzBuzzResult;
import com.example.demo.fizzbuzz.service.FizzBuzzService;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * The unit tests for {@link FizzBuzzController}.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(FizzBuzzController.class)
public class FizzBuzzControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private FizzBuzzService service;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    private static final String URI = "/fizzbuzz/";
    
    @Test
    public void testWhenZeroPassedThanNothingReturned() throws Exception {
        Long input = 0L;
        FizzBuzzResult serviceReturn = new FizzBuzzResult("");
        String expected = objectMapper.writeValueAsString(serviceReturn);
        when(service.getFizzBuzz(input)).thenReturn(serviceReturn);
        String result = sendRequest(input).getContentAsString();
        
        Assert.assertEquals(getMessage(input, expected, result), expected, result);
        verify(service).getFizzBuzz(input);
    }
    
    @Test
    public void testWhenNullPassedThanNothingReturned() throws Exception {
        Long input = null;
        when(service.getFizzBuzz(input)).thenReturn(new FizzBuzzResult(""));
        sendBadRequest(input);
        verify(service, times(0)).getFizzBuzz(input);
    }
    
    @Test
    public void testWhenInputCanBeDividedBy3ReturnsFIZZ() throws Exception {
        long input = 3L;
        FizzBuzzResult serviceReturn = new FizzBuzzResult("FIZZ");
        String expected = objectMapper.writeValueAsString(serviceReturn);
        when(service.getFizzBuzz(input)).thenReturn(serviceReturn);
        String result = sendRequest(input).getContentAsString();
    
        Assert.assertEquals(getMessage(input, expected, result), expected, result);
        verify(service).getFizzBuzz(input);
        
        input = 9L;
        when(service.getFizzBuzz(input)).thenReturn(serviceReturn);
        result = sendRequest(input).getContentAsString();
    
        Assert.assertEquals(getMessage(input, expected, result), expected, result);
        verify(service).getFizzBuzz(input);
    }
    
    @Test
    public void testWhenInputCanBeDividedBy5ReturnsBUZZ() throws Exception {
        long input = 5L;
        FizzBuzzResult serviceReturn = new FizzBuzzResult("BUZZ");
        String expected = objectMapper.writeValueAsString(serviceReturn);
        when(service.getFizzBuzz(input)).thenReturn(serviceReturn);
        String result = sendRequest(input).getContentAsString();
    
        Assert.assertEquals(getMessage(input, expected, result), expected, result);
        verify(service).getFizzBuzz(input);
    
        input = -50L;
        when(service.getFizzBuzz(input)).thenReturn(serviceReturn);
        result = sendRequest(input).getContentAsString();
    
        Assert.assertEquals(getMessage(input, expected, result), expected, result);
        verify(service).getFizzBuzz(input);
    }
    
    @Test
    public void testWhenInputCanBeDividedBy3And5ReturnsFIZZBUZZ() throws Exception {
        long input = 15L;
        FizzBuzzResult serviceReturn = new FizzBuzzResult("FIZZBUZZ");
        String expected = objectMapper.writeValueAsString(serviceReturn);
        when(service.getFizzBuzz(input)).thenReturn(serviceReturn);
        String result = sendRequest(input).getContentAsString();
    
        Assert.assertEquals(getMessage(input, expected, result), expected, result);
        verify(service).getFizzBuzz(input);
    
        input = -60L;
        when(service.getFizzBuzz(input)).thenReturn(serviceReturn);
        result = sendRequest(input).getContentAsString();
    
        Assert.assertEquals(getMessage(input, expected, result), expected, result);
        verify(service).getFizzBuzz(input);
    }
    
    @Test
    public void testWhenInputCantBeDividedBy3Or5ReturnsNothing() throws Exception {
        long input = 7L;
        FizzBuzzResult serviceReturn = new FizzBuzzResult("");
        String expected = objectMapper.writeValueAsString(serviceReturn);
        when(service.getFizzBuzz(input)).thenReturn(serviceReturn);
        String result = sendRequest(input).getContentAsString();
    
        Assert.assertEquals(getMessage(input, expected, result), expected, result);
        verify(service).getFizzBuzz(input);
    
        input = -1L;
        when(service.getFizzBuzz(input)).thenReturn(serviceReturn);
        result = sendRequest(input).getContentAsString();
    
        Assert.assertEquals(getMessage(input, expected, result), expected, result);
        verify(service).getFizzBuzz(input);
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
