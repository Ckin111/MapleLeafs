package com.estore.api.estoreapi.persistence;

import java.io.IOException;

import com.estore.api.estoreapi.model.Jersey;
import com.estore.api.estoreapi.model.User;

public interface UserDAO {
    
    User createUser(User user) throws IOException;

    boolean deleteUser(int id) throws IOException;

    User getUser(String username) throws IOException;

    Jersey[] getCart(String name) throws IOException;

    Jersey addJersey(String name, Jersey jersey) throws IOException;

    public boolean removeJersey(String name, Jersey jersey) throws IOException;
}
