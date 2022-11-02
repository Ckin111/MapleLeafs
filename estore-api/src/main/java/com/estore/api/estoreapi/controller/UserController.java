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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
     * @return Response Entity with a user
     */
    @GetMapping("/{name}")
    public ResponseEntity<User> getUser(@PathVariable String name) {
        LOG.info("GET /jerseys/" + name);
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
    public ResponseEntity<User> createUser (@RequestParam String name)
    {
        LOG.info("POST /users/?name=" + name);
        
        try{
            User user = userDAO.createUser(name);
            if (user != null)
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
    * Deletes a user with the given string name.
    *
    * @param name name of the user
    *
    * @return ResponseEntity with the status of OK if deleted, 
    * NOT_FOUND if not found, INTERNAL_SERVER_ERROR otherwise
    */
    @DeleteMapping("/{name}")
    public ResponseEntity<User> deleteUser(@PathVariable String name) {
        LOG.info("DELETE /users/" + name);
        try{ 
            if(userDAO.deleteUser(name)){
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
}
