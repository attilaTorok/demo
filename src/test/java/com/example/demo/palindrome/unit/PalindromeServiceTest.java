package com.example.demo.palindrome.unit;

import com.example.demo.palindrome.service.PalindromeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * The unit tests for {@link PalindromeService}.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PalindromeServiceTest {
    
    @Autowired
    private PalindromeService service;
    @Test
    public void testWhenEmptyOrOneCharacterPassedThenIsPalindrome() {
        String palindrome = "";
        boolean isPalindrome = service.isPalindrome(palindrome).isPalindrome();
        Assert.assertTrue(getTrueMessage(palindrome, isPalindrome), isPalindrome);
        
        palindrome = "x";
        isPalindrome = service.isPalindrome(palindrome).isPalindrome();
        Assert.assertTrue(getTrueMessage(palindrome, isPalindrome), isPalindrome);
    }
    
    @Test
    public void testWhenNullPassedThenIsNotPalindrome() {
        String palindrome = null;
        boolean isPalindrome = service.isPalindrome(palindrome).isPalindrome();
        Assert.assertFalse(getFalseMessage(palindrome, isPalindrome), isPalindrome);
    }
    
    @Test
    public void testWhenDatePassedThenIsPalindrome() {
        String palindrome = "10/02/2001";
        boolean isPalindrome = service.isPalindrome(palindrome).isPalindrome();
        Assert.assertTrue(getTrueMessage(palindrome, isPalindrome), isPalindrome);
        
        palindrome = "11/11/2002 11:11";
        isPalindrome = service.isPalindrome(palindrome).isPalindrome();
        Assert.assertTrue(getTrueMessage(palindrome, isPalindrome), isPalindrome);
    }
    
    @Test
    public void testWhenNotPalindromePassedThanNotPalindrome() {
        String palindrome = "This is not a palindrom.";
        boolean isPalindrome = service.isPalindrome(palindrome).isPalindrome();
        Assert.assertFalse(getFalseMessage(palindrome, isPalindrome), isPalindrome);
    }
    
    @Test
    public void testWhenPalindromePassedThenIsPalindrome() {
        String palindrome = "In girum imus nocte et consumimur igni";
        boolean isPalindrome = service.isPalindrome(palindrome).isPalindrome();
        Assert.assertTrue(getTrueMessage(palindrome, isPalindrome), isPalindrome);
    }
    
    @Test
    public void testWhenNumberPalindromePassedThenIsPalindrome() {
        String palindrome = "1221";
        boolean isPalindrome = service.isPalindrome(palindrome).isPalindrome();
        Assert.assertTrue(getTrueMessage(palindrome, isPalindrome), isPalindrome);
    }
    
    @Test
    public void testWhenOddLengthPalindromePassedThenIsPalindrome() {
        String palindrome = "12321";
        boolean isPalindrome = service.isPalindrome(palindrome).isPalindrome();
        Assert.assertTrue(getTrueMessage(palindrome, isPalindrome), isPalindrome);
    }
    
    @Test
    public void testWhenEvenLengthPalindromePassedThenIsPalindrome() {
        String palindrome = "1221";
        boolean isPalindrome = service.isPalindrome(palindrome).isPalindrome();
        Assert.assertTrue(getTrueMessage(palindrome, isPalindrome), isPalindrome);
    }
    
    private static String getTrueMessage(String input, boolean result) {
        return input == null ? "Null" : input + " should return " + true + " and not " + result;
    }
    
    private static String getFalseMessage(String input, boolean result) {
        return input == null ? "Null" : input + " should return " + false + " and not " + result;
    }
    
    
}
