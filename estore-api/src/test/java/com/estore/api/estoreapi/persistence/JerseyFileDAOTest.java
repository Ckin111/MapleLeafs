package com.estore.api.estoreapi.persistence;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;

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

}
