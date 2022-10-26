package com.estore.api.estoreapi.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estore.api.estoreapi.model.User;
import com.estore.api.estoreapi.persistence.UserDAO;

@RestController
@RequestMapping("users")
public class UserController {
    private UserDAO userDAO;

    public UserController(UserDAO userDAO){
        this.userDAO = userDAO;
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
        try{
            if (userDAO.createUser(user) != null)
                return new ResponseEntity<User>(user, HttpStatus.CREATED);
            else
                return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        catch(IOException e) {
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
        try{ 
            if(userDAO.deleteUser(id)){
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch(IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
