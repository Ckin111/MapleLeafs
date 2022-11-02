package com.estore.api.estoreapi.persistence;

import java.io.IOException;

import com.estore.api.estoreapi.model.User;

public interface UserDAO {
    
    /**
     * Given a user, this function will add the user to the user array and return
     * the user that was added
     * @param user the user to add
     * @return the user that was added
     * @throws IOException
     */
    User createUser(User user) throws IOException;

    /**
     * Delete a user from the array, returning true if removed, false otherwise
     * @param id the id of the user to remove
     * @return whether it was deleted or not
     * @throws IOException
     */
    boolean deleteUser(String name) throws IOException;

    /**
     * Gets a user from the array given the username
     * @param username of the user to get
     * @return a user
     * @throws IOException
     */
    User getUser(String username) throws IOException;
}
