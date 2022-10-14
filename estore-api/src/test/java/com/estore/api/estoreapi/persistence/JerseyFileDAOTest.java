package com.estore.api.estoreapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

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
     * @throws IOException
     */
    @BeforeEach
    public void setupJerseyFileDAO() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class);
        testJerseys = new Jersey[5];
        testJerseys[0] = new Jersey(0, "Matt", 39.99f, Size.SMALL, false, 16);
        testJerseys[1] = new Jersey(1, "Dave", 39.99f, Size.MEDIUM, true, 3);
        testJerseys[2] = new Jersey(2, "Sidney", 50f, Size.LARGE, false, 29);
        testJerseys[3] = new Jersey(3, "Aaron", 50f, Size.XL, false, 5);
        testJerseys[4] = new Jersey(4, "Derek", 39.99f, Size.SMALL, true, 6);

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
        Jersey jersey = new Jersey(5, "Terry", 24.32f, Size.MEDIUM, false, 32);
        
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
        Jersey jersey = new Jersey(0, "Matt", 39.99f, Size.SMALL, false, 16);
        
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

    @Test
    public void testGetJersey() throws IOException {
        // Invoke
        Jersey jersey = jerseyFileDAO.getJersey(0);

        // Analzye
        assertEquals(jersey,testJerseys[0]);
    }

    @Test
    public void testGetJerseys() throws IOException{
        // Invoke
        Jersey[] jerseys = jerseyFileDAO.getJerseys();

        // Analyze
        assertEquals(jerseys.length,testJerseys.length);
        for (int i = 0; i < testJerseys.length;++i)
            assertEquals(jerseys[i],testJerseys[i]);
    }

    @Test
    public void testDeleteJersey() throws IOException{
        // Invoke
        boolean result = jerseyFileDAO.deleteJersey(0);

        // Analzye
        assertEquals(result,true);
        // We check the internal tree map size against the length
        // of the test Jerseys array - 1 (because of the delete)
        // Because Jerseys attribute of JerseyFileDAO is package private
        // we can access it directly
        assertEquals(jerseyFileDAO.jerseys.size(),testJerseys.length-1);
    }

    @Test
    public void testDeleteJerseyNotFound() {
        // Invoke
        boolean result = assertDoesNotThrow(() -> jerseyFileDAO.deleteJersey(98),
                                                "Unexpected exception thrown");

        // Analyze
        assertEquals(result,false);
        assertEquals(jerseyFileDAO.jerseys.size(),testJerseys.length);
    }

    @Test
    public void testSaveException() throws IOException {
        doThrow(new IOException())
            .when(mockObjectMapper)
                .writeValue(any(File.class), any(Jersey[].class));
        Jersey jersey = new Jersey(0, null, 0, null, false, 0);
        assertThrows(IOException.class, () -> jerseyFileDAO.createJersey(jersey), 
                                            "IOException not thrown");
    }

}
