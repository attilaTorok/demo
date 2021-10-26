package com.example.demo.palindrome.service;

import com.example.demo.palindrome.model.PalindromeResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class PalindromeService {
    
    /**
     *
     *
     * @param palindrome
     * @return
     */
    public PalindromeResult isPalindrome(String palindrome) {
        if (palindrome == null) {
            return new PalindromeResult(false);
        } else if (palindrome.length() == 0 || palindrome.length() == 1) {
            return new PalindromeResult(true);
        }
    
        palindrome = getDate(palindrome);
    
        if (palindrome.length() % 2 == 1) {
            palindrome = palindrome.replace(String.valueOf(palindrome.charAt(palindrome.length() / 2 - 1)), "");
        }
        
        palindrome = palindrome.replace(" ", "").toLowerCase();
        int palindromeLength = palindrome.length();
        for (int i = 0; i < palindromeLength / 2; i++) {
            if (palindrome.charAt(i) != palindrome.charAt(palindromeLength - 1 - i)) {
                return new PalindromeResult(false);
            }
        }
    
        return new PalindromeResult(true);
    }
    
    private String getDate(String palindrome) {
        String replace = palindrome.replace("/", "");
        
        if (palindrome.length() == 10 && replace.length() == 8 && StringUtils.isAlphanumeric(replace)) {
            palindrome = replace;
        } else {
            replace = replace.replace(":", "").replace(" ", "");
            if (palindrome.length() == 16 && replace.length() == 12 && StringUtils.isAlphanumeric(replace)) {
                palindrome = replace;
            }
        }
        
        return palindrome;
    }
    
}
