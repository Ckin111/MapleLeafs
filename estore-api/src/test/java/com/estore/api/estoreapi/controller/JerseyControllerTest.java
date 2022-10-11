package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.estore.api.estoreapi.model.Jersey;
import com.estore.api.estoreapi.model.Jersey.Size;
import com.estore.api.estoreapi.persistence.JerseyDAO;

@Tag("Controller-tier")
public class JerseyControllerTest {
    private JerseyController jerseyController;
    private JerseyDAO mockJerseyDAO;

    /**
     * Test whether when controller creates a new jersey it will return the jersey
     * created and give HTTP status of OK
     * @throws IOException
     */
    @Test
    public void testCreateJersey() throws IOException {
        //setup
        Jersey jersey = new Jersey(7, "Ming", (float) 32.58, Size.LARGE, false, 23);
        when(mockJerseyDAO.createJersey(jersey)).thenReturn(jersey);
        
        //Invoke
        ResponseEntity<Jersey> response = jerseyController.createJersey(jersey);
        
        //Analyze
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(jersey, response.getBody());
    }

    /**
     * Test CreateHero in controller
     * when given a null it should return conflict status
     * @throws IOException
     */
    @Test
    public void testCreateJerseyFailed() throws IOException {
        //Setup
        Jersey jersey = new Jersey(7, "Ming", (float) 32.58, Size.LARGE, false, 23);
        when(mockJerseyDAO.createJersey(jersey)).thenReturn(null);

        //Invoke
        ResponseEntity<Jersey> response = jerseyController.createJersey(jersey);

        //Analyze
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    /**
     * Test Create Hero
     * when IOException occurs an HttpStatus internal service is returned
     * @throws IOException
     */
    @Test
    public void testCreateJerseyHandleException() throws IOException {
        //Setup
        Jersey jersey = new Jersey(7, "Ming", (float) 32.58, Size.LARGE, false, 23);
        doThrow(new IOException()).when(mockJerseyDAO).createJersey(jersey);

        //Invoke
        ResponseEntity<Jersey> response = jerseyController.createJersey(jersey);
        
        //Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    /**
     * Test Search Jerseys
     * Should give status of OK when an array of jerseys is returned
     * @throws IOException
     */
    @Test
    public void testSearchJerseys() throws IOException {
        //Setup
        String searchString = "D";
        Jersey[] jerseys = new Jersey[2];
        jerseys[0] = new Jersey(0, "Dave", (float)(39.99), Size.MEDIUM, true, 3);
        jerseys[1] = new Jersey(1, "Derek", (float)(39.99), Size.SMALL, true, 6);
        when(mockJerseyDAO.findJersey(searchString)).thenReturn(jerseys);

        //Invoke
        ResponseEntity<Jersey[]> response = jerseyController.searchJerseys(searchString);

        //Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(jerseys, response.getBody());
    }

    /**
     * Test search jersey
     * When given an exception, should return status code of internal service error
     * @throws IOException
     */
    @Test
    public void testSearchJerseysHandleException() throws IOException {
        //Setup
        String searchString = "D";
        doThrow(new IOException()).when(mockJerseyDAO).findJersey(searchString);

        //Invoke
        ResponseEntity<Jersey[]> response = jerseyController.searchJerseys(searchString);

        //Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
