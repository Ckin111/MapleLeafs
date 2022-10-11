package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOError;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.estore.api.estoreapi.model.Jersey;
import com.estore.api.estoreapi.model.Jersey.Size;
import com.estore.api.estoreapi.persistence.JerseyDAO;

public class JerseyControllerTest {
    private JerseyController jerseyController;
    private JerseyDAO mockJerseyDAO;

    /**
     * Test whether when controller creates a new jersey it will return the jersey
     * created and give HTTP status of OK
     * @throws IOException
     */
    @Test
    public void testCreateHero() throws IOException {
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
    public void testCreateHeroFailed() throws IOException {
        //Setup
        Jersey jersey = new Jersey(7, "Ming", (float) 32.58, Size.LARGE, false, 23);
        when(mockJerseyDAO.createJersey(jersey)).thenReturn(null);

        //Invoke
        ResponseEntity<Jersey> response = jerseyController.createJersey(jersey);

        //Analyze
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }
}
