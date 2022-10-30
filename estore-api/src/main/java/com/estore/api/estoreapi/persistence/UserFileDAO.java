package com.estore.api.estoreapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.estore.api.estoreapi.model.Jersey;
import com.estore.api.estoreapi.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class UserFileDAO implements UserDAO{
    private static final Logger LOG = Logger.getLogger(JerseyFileDAO.class.getName());

    Map<Integer, User> users;
    private ObjectMapper objectMapper;
    private static int nextId;
    private String filename;

    public UserFileDAO(@Value("${users.file}") String filename, ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();
    }

    private synchronized static int nextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    // given a string, this function will return the user associated with the given string
    // return null if not found
    @Override
    public User getUser(String username) throws IOException {
        synchronized(users){
            for (User user : users.values()) {
                if (user.getName().equals(username)) {
                    return user;
                }
            }
        }

        return null;
    }

    // This will return the users array 
    private User[] getUsersArray() {
        return getUsersArray(null);
    }

    // given a string (filter), the function will return an array matching the filter
    private User[] getUsersArray(String containsText) { // if containsText == null, no filter
        ArrayList<User> userArrayList = new ArrayList<>();

        for (User user : users.values()) {
            if (containsText == null || user.getName().contains(containsText)) {
                userArrayList.add(user);
            }
        }

        User[] userArray = new User[userArrayList.size()];
        userArrayList.toArray(userArray);
        return userArray;
    }

    // load Jersey from the JSON into a map, will return true if read successfully 
    private boolean load() throws IOException {
        users = new TreeMap<>();
        nextId = 0;

        User[] userArray = objectMapper.readValue(new File(filename), User[].class);

        for(User user: userArray) {
            users.put(user.getId(), user);
            if (user.getId() > nextId) {
                nextId = user.getId();
            }
        }

        ++nextId;
        return true;
    }

    // Saves the Jerseys from the map into the file as an array of JSON objects, return true if the Users were written successfully
    private boolean save() throws IOException {
        User[] userArray = getUsersArray();
        
        objectMapper.writeValue(new File(filename), userArray);
        return true;
    }

    @Override
    public User createUser(User user) throws IOException {
        synchronized(users){
            for (User use : users.values()) {
                if(user.sameName(use.getName())) {
                    return null;
                }
            }
            Jersey[] emptyCart = new Jersey[0];
            User newUser = new User(nextId(),user.getName(),emptyCart);
            users.put(newUser.getId(), newUser);
            save();
            return newUser;
        }
    }

    @Override
    public boolean deleteUser(int id) throws IOException {
        synchronized(users) {
            if (users.containsKey(id)) {
                users.remove(id);
                return save();
            }
            else
                return false;
        }
    }

    //gets the user's cart
    @Override
    public Jersey[] getCart(String name) throws IOException {
        User user = getUser(name);
        if(user != null) {
            return user.getCart();
        }
        return null;
    }

    //adds a jersey to the specified user's cart
    @Override
    public Jersey addJersey(String name, Jersey jersey) throws IOException {
        User user = getUser(name);
        if(user != null) {
            Jersey response = user.addJersey(jersey);
            save();
            return response;
        }
        return null;
    }
}
