package com.example.demo.fizzbuzz.service;

import com.example.demo.fizzbuzz.model.FizzBuzzResult;
import org.springframework.stereotype.Service;

/**
 * The service to calculate determine FIZZBUZZ.
 */
@Service
public class FizzBuzzService {
    
    /**
     * Get "FIZZ", "BUZZ" or "FIZZBUZZ" determined by the parameter.
     *
     * @param number the number
     * @return "FIZZ", if the given number can be divided by 3, without reminder. <br>
     * "BUZZ", if the given number can be divided by 5, without reminder. <br>
     * "FIZZBUZZ", if the given number can be divided by 3 and 5, without reminder.
     */
    public FizzBuzzResult getFizzBuzz(Long number) {
        if (number == null || number == 0) {
            return new FizzBuzzResult("");
        }
        
        String result = "";
        if (number % 3 == 0) result = "FIZZ";
        if (number % 5 == 0) result += "BUZZ";
        
        return new FizzBuzzResult(result);
    }
    
}
