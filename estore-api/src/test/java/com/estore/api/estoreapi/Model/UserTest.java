package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.estore.api.estoreapi.model.Jersey.Size;

@Tag("Model-Tier")
public class UserTest {

    /**
     * Test the constructor of User Java Class along with the get methods
     */
    @Test
    public void testCtor() {
        //Setup
        int expected_id = 1;
        String expected_username = "SuperSally45";
        Jersey[] expected_cart = new Jersey[5];
        expected_cart[0] = new Jersey(0, "Matt", 39.99f, Size.SMALL, false, 16);
        expected_cart[1] = new Jersey(1, "Dave", 39.99f, Size.MEDIUM, true, 3);
        expected_cart[2] = new Jersey(2, "Sidney", 50f, Size.LARGE, false, 29);
        expected_cart[3] = new Jersey(3, "Aaron", 50f, Size.XL, false, 5);
        expected_cart[4] = new Jersey(4, "Derek", 39.99f, Size.SMALL, true, 6);

        //Invoke
        User user = new User(expected_id, expected_username, expected_cart);

        //Analyze
        assertEquals(expected_id, user.getId());
        assertEquals(expected_username, user.getName());
        assertEquals(expected_cart, user.getCart());
    }

    /**
     * Test the equals method of User class. They should be equal if
     * they have the same id. If they have different id's they should not be equal
     */
    @Test
    public void testEquals() {
        //Setup
        int expected_id = 1;
        String expected_username = "Ming";
        Jersey[] expected_cart = null;
        User user1 = new User(expected_id, expected_username, expected_cart);
        User user2 = new User(expected_id, expected_username + " ", expected_cart);

        //Inovke
        boolean same_result = user1.equals(user1);
        boolean different_result = user1.equals(user2);

        //Analyze
        assertEquals(true, same_result);
        assertEquals(false, different_result);
    }

    /**
     * Tests the method sameName in User class
     * Should be the same if the given string is the same as the user's name
     * Should be different if the given string is not the same as the user's name
     */
    @Test
    public void testSameName() {
        //Setup
        int expected_id = 1;
        String expected_username = "Ming";
        Jersey[] expected_cart = null;
        User user = new User(expected_id, expected_username, expected_cart);

        //Invoke/Analyze
        assertTrue(user.sameName(expected_username));
        assertFalse(user.sameName("Test"));
    }
    
}
