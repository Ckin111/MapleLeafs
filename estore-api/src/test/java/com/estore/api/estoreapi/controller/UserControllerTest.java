package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.estore.api.estoreapi.model.Jersey;
import com.estore.api.estoreapi.model.User;
import com.estore.api.estoreapi.persistence.UserDAO;

import net.bytebuddy.agent.VirtualMachine.ForHotSpot.Connection.Response;

@Tag("Controller-Tier")
public class UserControllerTest {
    private UserController userController;
    private UserDAO mockUserDAO;

    @BeforeEach
    public void setupUserController() {
        mockUserDAO = mock(UserDAO.class);
        userController = new UserController(mockUserDAO);
    }

    @Test
    public void testGetUser() throws IOException {
        //Setup
        User user = new User(0, "Hi", null);
        when(mockUserDAO.getUser(user.getName())).thenReturn(user);

        //Invoke
        ResponseEntity<User> response = userController.getUser(user.getName());

        //Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testGetUserNotFount() throws IOException {
        //Setup
        String username = "NotFound";
        when(mockUserDAO.getUser(username)).thenReturn(null);

        //Invoke
        ResponseEntity<User> response = userController.getUser(username);

        //Analyze
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetUserHandleException() throws IOException {
        //Setup
        String username = "Exception";
        doThrow(IOException.class).when(mockUserDAO).getUser(username);

        //Invoke
        ResponseEntity<User> response = userController.getUser(username);

        //Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testCreateUser() throws IOException {
        //Setup
        String username = "Test";
        User user = new User(0, username, new Jersey[0]);
        when(mockUserDAO.createUser(username)).thenReturn(user);

        //Invoke
        ResponseEntity<User> response = userController.createUser(username);

        //Analyze
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testCreateUserConflict() throws IOException {
        //Setup
        String username = "Test";
        when(mockUserDAO.createUser(username)).thenReturn(null);

        //Invoke
        ResponseEntity<User> response = userController.createUser(username);

        //Analyze
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testCreateUserHandleException() throws IOException {
        //Setup
        String username = "Test";
        doThrow(IOException.class).when(mockUserDAO).createUser(username);

        //Invoke
        ResponseEntity<User> response = userController.createUser(username);

        //Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testDeleteUser() throws IOException {
        //Setup
        String username = "Test";
        when(mockUserDAO.deleteUser(username)).thenReturn(true);

        //Invoke
        ResponseEntity<User> response = userController.deleteUser(username);

        //Analyze
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeleteUserNotFound() throws IOException {
        //Setup
        String username = "Test";
        when(mockUserDAO.deleteUser(username)).thenReturn(false);

        //Invoke
        ResponseEntity<User> response = userController.deleteUser(username);

        //Analyze
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteUserHandleException() throws IOException {
        //Setup
        String username = "Test";
        doThrow(IOException.class).when(mockUserDAO).deleteUser(username);

        //Invoke
        ResponseEntity<User> response = userController.deleteUser(username);

        //Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
