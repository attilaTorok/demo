package com.example.demo.palindrome.model;

import com.example.demo.palindrome.service.PalindromeService;
import lombok.Value;

/**
 * Immutable result object for {@link PalindromeService#isPalindrome(String)}.
 */
@Value
public class PalindromeResult {
    private boolean isPalindrome;
}
