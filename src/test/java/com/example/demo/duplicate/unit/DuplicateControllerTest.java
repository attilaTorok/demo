package com.example.demo.duplicate.unit;

import com.example.demo.duplicate.controller.DuplicateController;
import com.example.demo.duplicate.service.DuplicateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * The unit tests for {@link DuplicateController}.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(DuplicateController.class)
public class DuplicateControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private DuplicateService service;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    private static final String URI = "/duplicates/";
    
    @Test
    public void testWhenNoMultipleElementsThenEmptyListReturned() throws Exception {
        List<Integer> input = List.of(1);
        String expected = "[]";
        when(service.getDuplicates(input)).thenReturn(Collections.emptyList());
        String result = sendRequest(input).getContentAsString();
        
        Assert.assertEquals(printList(input) + " should return " + expected + " and not "
                + result, expected, result);
        verify(service).getDuplicates(input);
    }
    
    @Test
    public void testWhenNullPassedThenEmptyListReturned() throws Exception {
        List<? extends Number> input = Collections.emptyList();
        String expected = "[]";
        doReturn(input).when(service).getDuplicates(input);
        String result = sendRequest(input).getContentAsString();
        
        Assert.assertEquals("Null should return " + expected + " and not "
                + result, expected, result);
        verify(service).getDuplicates(input);
    }
    
    
    @Test
    public void testWhenNumbersPassedThanInReverseOrder() throws Exception {
        List<? extends Number> input = List.of(1, 2, 3, 4, 2, 3);
        String expected = "[3,2]";
        doReturn(List.of(3, 2)).when(service).getDuplicates(input);
        String result = sendRequest(input).getContentAsString();
        
        Assert.assertEquals(printList(input) + "should return " + expected + " and not "
                + result, expected, result);
        Mockito.verify(service).getDuplicates(input);
    }
    
    @Test
    public void testWhenOnlyOneKindOfNumbersPassedThanOnlyOneNumberReturned() throws Exception {
        List<Integer> input = List.of(1, 1, 1, 1, 1, 1, 1);
        String expected = "[1]";
        doReturn(List.of(1)).when(service).getDuplicates(input);
        String result = sendRequest(input).getContentAsString();
        
        Assert.assertEquals(printList(input) + "should return " + expected + " and not "
                + result, expected, result);
        Mockito.verify(service).getDuplicates(input);
    }
    
    @Test
    public void testWhenMultipleTypeOfNumberPassed() throws Exception {
        List<? extends Number> input = List.of(1.1, 1, 1.4, 1.1, 1.3, 1.2, -1, -1, 1.4);
        String expected = "[1.4,1,-1]";
        doReturn(List.of(1.4, 1, -1)).when(service).getDuplicates(input);
        String result = sendRequest(input).getContentAsString();
        
        Assert.assertEquals(printList(input) + " should return " + expected + " and not "
                + result, expected, result);
        Mockito.verify(service).getDuplicates(input);
    }
    
    @Test
    public void testWhenListOfStringPassedThanClientErrorReturned() throws Exception {
        List<String> input = List.of("a", "a");
        sendBadRequest(input);
    }
    
    @Test
    public void testWhenNullPassedThanClientErrorReturned() throws Exception {
        sendBadRequest(null);
    }
    
    private String printList(List<? extends Number> numbers) {
        return numbers.stream().map(Object::toString).collect(Collectors.joining(", "));
    }
    
    private MockHttpServletResponse sendRequest(List<? extends Number> numbers) throws Exception {
        return this.mockMvc.perform(post(URI)
                .content(objectMapper.writeValueAsString(numbers))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();
    }
    
    private void sendBadRequest(List<?> list) throws Exception {
        this.mockMvc.perform(post(URI)
                .content(objectMapper.writeValueAsString(list))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
    
}
