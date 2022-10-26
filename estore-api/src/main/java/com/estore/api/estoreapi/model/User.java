package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    @JsonProperty("id") private int id;
    @JsonProperty("username") private String username;

    public User(@JsonProperty("id") int id, @JsonProperty("username") String username){
        this.id = id;
        this.username = username;
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

}
