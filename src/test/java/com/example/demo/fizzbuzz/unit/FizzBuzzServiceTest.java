package com.example.demo.fizzbuzz.unit;

import com.example.demo.fizzbuzz.model.FizzBuzzResult;
import com.example.demo.fizzbuzz.service.FizzBuzzService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * The unit tests for {@link FizzBuzzService}.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FizzBuzzServiceTest {
    
    @Autowired
    private FizzBuzzService service;
    
    @Test
    public void testWhenZeroPassedThanNothingReturned() {
        long input = 0L;
        FizzBuzzResult result = service.getFizzBuzz(input);
        Assert.assertEquals(getMessage(input, "nothing", result), "", result.getResult());
    }
    
    @Test
    public void testWhenNullPassedThanNothingReturned() {
        Long input = null;
        FizzBuzzResult result = service.getFizzBuzz(input);
        Assert.assertEquals(getMessage(input, "nothing", result), "", result.getResult());
    }
    
    @Test
    public void testWhenInputCanBeDividedBy3ReturnsFIZZ() {
        long input = 3L;
        String expected = "FIZZ";
        FizzBuzzResult result = service.getFizzBuzz(input);
        Assert.assertEquals(getMessage(input, expected, result), expected, result.getResult());
        
        input = 9L;
        result = service.getFizzBuzz(input);
        Assert.assertEquals(getMessage(input, expected, result), expected, result.getResult());
    }
    
    @Test
    public void testWhenInputCanBeDividedBy5ReturnsBUZZ() {
        long input = 5L;
        String expected = "BUZZ";
        FizzBuzzResult result = service.getFizzBuzz(input);
        Assert.assertEquals(getMessage(input, expected, result), expected, result.getResult());
        
        input = -50L;
        result = service.getFizzBuzz(input);
        Assert.assertEquals(getMessage(input, expected, result), expected, result.getResult());
    }
    
    @Test
    public void testWhenInputCanBeDividedBy3And5ReturnsFIZZBUZZ() {
        long input = 15L;
        String expected = "FIZZBUZZ";
        FizzBuzzResult result = service.getFizzBuzz(input);
        Assert.assertEquals(getMessage(input, expected, result), expected, result.getResult());
        
        input = -60L;
        result = service.getFizzBuzz(input);
        Assert.assertEquals(getMessage(input, expected, result), expected, result.getResult());
    }
    
    @Test
    public void testWhenInputCantBeDividedBy3Or5ReturnsNothing() {
        long input = 7L;
        String expected = "";
        FizzBuzzResult result = service.getFizzBuzz(input);
        Assert.assertEquals(getMessage(input, expected, result), expected, result.getResult());
        
        input = -1L;
        result = service.getFizzBuzz(input);
        Assert.assertEquals(getMessage(input, expected, result), expected, result.getResult());
    }
    
    private static String getMessage(Long input, String expected, FizzBuzzResult result) {
        return input == null ? "Null" : input + " should return " + expected + " and not " + result.getResult();
    }
    
}
