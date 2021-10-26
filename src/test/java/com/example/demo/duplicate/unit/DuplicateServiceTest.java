package com.example.demo.duplicate.unit;

import com.example.demo.duplicate.service.DuplicateService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The unit tests for {@link DuplicateService}.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DuplicateServiceTest {
    
    @Autowired
    private DuplicateService service;
    
    @Test
    public void testWhenNoMultipleElementsThenEmptyListReturned() {
        List<Integer> input = List.of(1);
        List<? extends Number> duplicates = service.getDuplicates(input);
        List<Integer> expected = Collections.emptyList();
        Assert.assertEquals(printList(input) + " should return " + printList(expected) + " and not "
                + printList(duplicates), expected, duplicates);
    }
    
    @Test
    public void testWhenNullPassedThenEmptyListReturned() {
        List<Integer> input = null;
        List<? extends Number> duplicates = service.getDuplicates(input);
        List<Integer> expected = Collections.emptyList();
        Assert.assertEquals("Null should return " + printList(expected) + " and not "
                + printList(duplicates), expected, duplicates);
    }
    
    @Test
    public void testWhenNumbersPassedThanInReverseOrder() {
        List<Integer> input = List.of(1, 2, 3, 4, 2, 3);
        List<? extends Number> duplicates = service.getDuplicates(input);
        List<Integer> expected = List.of(3, 2);
        Assert.assertEquals(printList(input) + " should return " + printList(expected) + " and not "
                + printList(duplicates), expected, duplicates);
    }
    
    @Test
    public void testWhenOnlyOneKindOfNumbersPassedThanOnlyOneNumberReturned() {
        List<Integer> input = List.of(1, 1, 1, 1, 1, 1, 1);
        List<? extends Number> duplicates = service.getDuplicates(input);
        List<Integer> expected = List.of(1);
        Assert.assertEquals(printList(input) + " should return " + printList(expected) + " and not "
                + printList(duplicates), expected, duplicates);
    }
    
    @Test
    public void testWhenMultipleTypeOfNumberPassed() {
        List<Number> input = List.of(1.1, 1, 1.4, 1.1, 1.3, 1.2, -1, -1, 1.4);
        List<? extends Number> duplicates = service.getDuplicates(input);
        List<Number> expected = List.of(1.4, 1.1, -1);
        Assert.assertEquals(printList(input) + " should return " + printList(expected) + " and not "
                + printList(duplicates), expected, duplicates);
    }
    
    private String printList(List<? extends Number> numbers) {
        return numbers.stream().map(Object::toString).collect(Collectors.joining(","));
    }
    
}
