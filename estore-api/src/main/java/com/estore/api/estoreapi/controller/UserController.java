package com.estore.api.estoreapi.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estore.api.estoreapi.model.Jersey;
import com.estore.api.estoreapi.model.User;
import com.estore.api.estoreapi.persistence.UserDAO;


@RestController
@RequestMapping("users")
public class UserController {
    private UserDAO userDAO;

    private static final Logger LOG = Logger.getLogger(UserController.class.getName());


    public UserController(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    /**
     * Returns user whose username matches the given string name
     * @param name the username to find
     * @return Response Entity with a user and status of OK if successful, if not
     *  give status code NOT_FOUND, otherwise INTERNAL_SERVICE_ERROR
     */
    @GetMapping("/{name}")
    public ResponseEntity<User> getUser(@PathVariable String name) {
        LOG.info("GET /users/" + name);
        try {
            User user = userDAO.getUser(name);
            if (user != null)
                return new ResponseEntity<User>(user,HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
    * Creates a user with the same data as input
    *
    * @param user the user to be created
    *
    * @return ResponseEntity with user created and status CREATED if successful, if not, 
    * status CONFLICT, otherwise, status INTERNAL_SERVER_ERROR.
    */
    @PostMapping("")
    public ResponseEntity<User> createUser (@RequestBody User user)
    {
        LOG.info("POST /users " + user);
        
        try{
            if (userDAO.createUser(user) != null)
                return new ResponseEntity<User>(user, HttpStatus.CREATED);
            else
                return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
    * Deletes a user with the id id.
    *
    * @param id id of the user
    *
    * @return ResponseEntity with the status of OK if deleted, 
    * NOT_FOUND if not found, INTERNAL_SERVER_ERROR otherwise
    */
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {
        LOG.info("DELETE /users/" + id);
        try{ 
            if(userDAO.deleteUser(id)){
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Gets a users cart given a specific user
     * @param user the user that wants to see their cart
     * @return response entity with an array of Jerseys if successful, if not
     *  response entity of NOT_FOUND, otherwise INTERNAL_SERVICE_ERROR
     */
    @GetMapping("/{name}/cart")
    public ResponseEntity<Jersey[]> getCart(@PathVariable String name) {
        LOG.info("GET /users/" + name + "/cart");
        try{
            Jersey[] cart = userDAO.getCart(name);
            if(cart == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                return new ResponseEntity<Jersey[]>(cart, HttpStatus.OK);
            }
        }
        catch(IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Finds the user with the given name, then adds the given jersey to user's cart
     * @param name the user to find
     * @param jersey the jersey to add
     * @return response entity with the jersey that was added if successful, if not,
     *  status code of NOT_FOUND, otherwise, INTERNAL_SERVICE_ERROR
     */
    @PostMapping("/{name}/cart")
    public ResponseEntity<Jersey> addToCart(@PathVariable String name, @RequestBody Jersey jersey) {
        LOG.info(" POST /users/" + name + "/cart" + jersey);
        try{
            Jersey response = userDAO.addJersey(name, jersey);
            if(response == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                return new ResponseEntity<Jersey>(response, HttpStatus.OK);
            }
        }
        catch(IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
