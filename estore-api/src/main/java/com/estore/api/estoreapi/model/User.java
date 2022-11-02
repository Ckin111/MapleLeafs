package com.estore.api.estoreapi.model;

import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User implements Comparable{
    private static final Logger LOG = Logger.getLogger(Jersey.class.getName());

    @JsonProperty("id") private int id;
    @JsonProperty("username") private String username;
    @JsonProperty("cart") private Jersey[] cart;

    public User(@JsonProperty("id") int id, @JsonProperty("username") String username, @JsonProperty("cart") Jersey[] cart){
        this.id = id;
        this.username = username;
        this.cart = cart;
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

    public Jersey[] getCart(){
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
            }
        }
        return false;
    }

    public boolean sameName(String name) {
        if(this.getName().equals(name)) {
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Object o) {
        User object = (User)o;
        return this.getId()-object.getId();
    }

}
