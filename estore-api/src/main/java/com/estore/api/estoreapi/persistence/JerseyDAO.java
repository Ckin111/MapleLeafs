package com.estore.api.estoreapi.persistence;
import java.io.IOException;
import com.estore.api.estoreapi.model.Jersey;

public interface JerseyDAO {
    Jersey[] getJerseys() throws IOException;

    Jersey[] findJersey(String text) throws IOException;

    Jersey getJersey(int id) throws IOException;

    Jersey createJersey(Jersey jersey) throws IOException;

    Jersey updateJersey(Jersey jersey) throws IOException;

    boolean deleteJersey(int id) throws IOException;

}
