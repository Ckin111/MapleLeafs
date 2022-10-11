package com.estore.api.estoreapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;

import com.estore.api.estoreapi.model.Jersey;
import com.estore.api.estoreapi.model.Jersey.Size;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Test Jersey File DAO class
 * 
 * @author Maple leaf team
 */
@Tag("Persistence-tier")
public class JerseyFileDAOTest {
    JerseyFileDAO jerseyFileDAO;
    Jersey[] testJerseys;
    ObjectMapper mockObjectMapper;


    /**
     * Before each test, create a new jersey File DAO with an injection
     * of a Mock object Mapper so that we separate tests from actual file
     */
    @BeforeEach
    public void setupJerseyFileDAO() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class);
        testJerseys = new Jersey[5];
        testJerseys[0] = new Jersey(0, "Matt", (float)(39.99), Size.SMALL, false, 16);
        testJerseys[1] = new Jersey(1, "Dave", (float)(39.99), Size.MEDIUM, true, 3);
        testJerseys[2] = new Jersey(2, "Sidney", (float)(50), Size.LARGE, false, 29);
        testJerseys[3] = new Jersey(3, "Aaron", (float)(50), Size.XL, false, 5);
        testJerseys[4] = new Jersey(4, "Derek", (float)(39.99), Size.SMALL, true, 6);

        //When the object mapper reads from a file, the mock object mapper will
        // return jersey array above
        when(mockObjectMapper.readValue(new File("random.txt"), Jersey[].class))
            .thenReturn(testJerseys);
        jerseyFileDAO = new JerseyFileDAO("random.txt", mockObjectMapper);
    }

    /**
     * Test to determine whether jerseyFileDAO will return a new jersey 
     * given a jersey that doesn't already exist
     * @throws IOException
     */
    @Test
    public void testCreateJersey() throws IOException {
        //Setup Jersey to create
        Jersey jersey = new Jersey(5, "Terry", (float) 24.32, Size.MEDIUM, false, 32);
        
        //Invoke
        Jersey result = assertDoesNotThrow(() -> jerseyFileDAO.createJersey(jersey),
            "Unexpected exception thrown");
    
        //Analyze response
        assertNotNull(result);
        assertEquals(jerseyFileDAO.jerseys.size(), testJerseys.length+1);

        //Analyze whether created jersey is same as what we gave
        Jersey actual = jerseyFileDAO.getJersey(jersey.getId());
        assertEquals(actual.getName(), jersey.getName());
        assertEquals(actual.getCost(), jersey.getCost());
        assertEquals(actual.getId(), jersey.getId());
        assertEquals(actual.getIsHome(), jersey.getIsHome());
        assertEquals(actual.getNumber(), jersey.getNumber());
        assertEquals(actual.getSize(), jersey.getSize());
    }

    /**
     * Test to determine whether JerseyFileDAO will return null if
     * the new jersey is the same as an existing jersey
     * @throws IOException
     */
    @Test
    public void testCreateJerseyConflict() throws IOException {
        //Setup Jersey to create
        Jersey jersey = new Jersey(0, "Matt", (float)(39.99), Size.SMALL, false, 16);
        
        //Invoke
        Jersey result = jerseyFileDAO.createJersey(jersey);

        //Analyze
        assertNull(result);
        assertEquals(jerseyFileDAO.jerseys.size(), testJerseys.length);
    }

    /**
     * Test if JerseyFileDAO will find the jerseys with names that match
     * the given substring
     * @throws IOException
     */
    @Test
    public void testFindJerseys() throws IOException {
        //Invoke
        Jersey[] jerseys = jerseyFileDAO.findJersey("D");

        //Analyze
        assertEquals(jerseys.length, 2);
        assertEquals(jerseys[0], testJerseys[1]);
        assertEquals(jerseys[1], testJerseys[4]);
    }

}
