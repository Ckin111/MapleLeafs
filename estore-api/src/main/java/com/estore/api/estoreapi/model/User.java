package com.estore.api.estoreapi.model;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private static final Logger LOG = Logger.getLogger(Jersey.class.getName());

    @JsonProperty("id") private int id;
    @JsonProperty("username") private String username;
    @JsonProperty("cart") private Jersey[] cart;
    private ArrayList<Jersey> variableCart;

    public User(@JsonProperty("id") int id, @JsonProperty("username") String username, @JsonProperty("cart") Jersey[] cart){
        this.id = id;
        this.username = username;
        this.cart = cart;
        variableCart = new ArrayList<>();
        if(cart != null) {
            for(Jersey jersey: cart) {
            variableCart.add(jersey);
            }
        }
        
    }

    /**
     * gets the user's id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * gets the username
     * @return username
     */
    public String getName() {
        return username;
    }

    /**
     * Gets the user's shopping cart
     * @return an array of Jerseys
     */
    public Jersey[] getCart() {
        return cart;
    }

    @Override
    /**
     * Determines whether a User is equal to another User
     * They are equal if they have the same id
     * @return true if it is equal, false otherwise
     */
    public boolean equals(Object o){
        if(o instanceof User){
            User object = (User)(o);
            if(object.getId() == this.getId()){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    /**
     * Determines if a given string if the same as the user's name
     * @param name the string to check
     * @return true if it is the same, false otherwise
     */
    public boolean sameName(String name) {
        if(this.getName().equals(name)) {
            return true;
        }
        return false;
    }

}
