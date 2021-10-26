package com.example.demo.fizzbuzz.model;

import com.example.demo.fizzbuzz.service.FizzBuzzService;
import lombok.Value;

/**
 * Immutable result object for {@link FizzBuzzService#getFizzBuzz(Long)}.
 */
@Value
public class FizzBuzzResult {
    private String result;
}
