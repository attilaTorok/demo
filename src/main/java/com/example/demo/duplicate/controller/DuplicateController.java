package com.example.demo.duplicate.controller;

import com.example.demo.duplicate.service.DuplicateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The controller for handling /duplicates requests.
 */
@RestController
@RequestMapping("/duplicates")
public class DuplicateController {
    
    @Autowired
    private DuplicateService service;
    
    /**
     * Gets the duplicated numbers from the passed numbers.
     *
     * @param numbers the numbers
     * @return the duplicates
     */
    @PostMapping(value = "/", consumes = "application/json")
    public List<? extends Number> getDuplicates(@RequestBody List<Number> numbers) {
        return service.getDuplicates(numbers);
    }
    
}
