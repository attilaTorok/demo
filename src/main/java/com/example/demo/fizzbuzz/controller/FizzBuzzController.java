package com.example.demo.fizzbuzz.controller;

import com.example.demo.fizzbuzz.model.FizzBuzzResult;
import com.example.demo.fizzbuzz.service.FizzBuzzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The controller for handling /fizzbuzz requests.
 */
@RestController
@RequestMapping("/fizzbuzz")
public class FizzBuzzController {
    
    @Autowired
    private FizzBuzzService fizzBuzzService;
    
    /**
     * Handling post method for /fizzbuzz/.
     *
     * @param number the number
     * @return "FIZZ", if the given number can be divided by 3, without reminder. <br>
     * "BUZZ", if the given number can be divided by 5, without reminder. <br>
     * "FIZZBUZZ", if the given number can be divided by 3 and 5, without reminder.
     */
    @PostMapping("/")
    public FizzBuzzResult getFizzBuzz(@RequestBody Long number) {
        return fizzBuzzService.getFizzBuzz(number);
    }
    
}
