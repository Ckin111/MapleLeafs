package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.estore.api.estoreapi.model.Jersey;
import com.estore.api.estoreapi.model.Jersey.Size;

@Tag("Model-tier")
public class JerseyTest {
    @Test
    public void testCtor() {
        // Setup
        int expected_id = 7;
        String expected_name = "Dom";
        float expectedCost = 32.58f;
        Size expectedSize = Size.MEDIUM;
        boolean expectedIsHome = true;
        int expectedNumber = 93;
        

        // Invoke
        Jersey jersey = new Jersey(expected_id,expected_name,expectedCost,expectedSize,expectedIsHome,expectedNumber);

        // Analyze
        assertEquals(expected_id,jersey.getId());
        assertEquals(expected_name,jersey.getName());
        assertEquals(expectedCost,jersey.getCost());
        assertEquals(expectedSize,jersey.getSize());
        assertEquals(expectedIsHome,jersey.getIsHome());
        assertEquals(expectedNumber,jersey.getNumber());
    }

    
}