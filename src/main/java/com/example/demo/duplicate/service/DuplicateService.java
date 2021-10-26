package com.example.demo.duplicate.service;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The service to calculate duplicates.
 */
@Service
public class DuplicateService {
    
    /**
     * Finding the elements which are in the list multiple times, in reversed order.
     *
     * @param numbers the list
     * @return the calculated list
     */
    public List<? extends Number> getDuplicates(List<? extends Number> numbers) {
        if (numbers == null || numbers.size() < 2) {
            return Collections.emptyList();
        }
        
        return numbers.stream()
                .collect(Collectors.groupingBy(number -> number, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .sorted((x, y) -> x.doubleValue() > y.doubleValue() ? -1 : (x == y ? 0 : 1))
                .collect(Collectors.toList());
    }
    
}
