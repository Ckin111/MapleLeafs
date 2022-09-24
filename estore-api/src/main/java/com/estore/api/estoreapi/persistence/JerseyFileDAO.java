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
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JerseyFileDAO implements JerseyDAO {
    private static final Logger LOG = Logger.getLogger(JerseyFileDAO.class.getName());
    Map<Integer, Jersey> jerseys;
    private ObjectMapper objectMapper;
    private static int nextId;
    private String filename;

    public JerseyFileDAO(@Value("${jerseys.file}") String filename, ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();
    }

    private synchronized static int nextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    private Jersey[] getJerseysArray() {
        return getJerseysArray(null);
    }

    private Jersey[] getJerseysArray(String containsText) { // if containsText == null, no filter
        ArrayList<Jersey> jerseyArrayList = new ArrayList<>();

        for (Jersey jersey : jerseys.values()) {
            if (containsText == null || jersey.getName().contains(containsText)) {
                jerseyArrayList.add(jersey);
            }
        }

        Jersey[] jerseyArray = new Jersey[jerseyArrayList.size()];
        jerseyArrayList.toArray(jerseyArray);
        return jerseyArray;
    }

    private boolean load() throws IOException {
        jerseys = new TreeMap<>();
        nextId = 0;

        Jersey[] jerseyArray = objectMapper.readValue(new File(filename), Jersey[].class);

        for(Jersey jersey: jerseyArray) {
            jerseys.put(jersey.getId(), jersey);
            if (jersey.getId() > nextId) {
                nextId = jersey.getId();
            }
        }

        ++nextId;
        return true;
    }

    private boolean save() throws IOException {
        Jersey[] jerseyArray = getJerseysArray();
        
        objectMapper.writeValue(new File(filename), jerseyArray);
        return true;
    }

    @Override
    public Jersey getJersey(int id) throws IOException {
        synchronized(jerseys){
            if(jerseys.containsKey(id)){
                return jerseys.get(id);
            }else{
                return null;
            }
        }
    }

    public Jersey createJersey(Jersey jersey) throws IOException {
        synchronized(jerseys){
            Jersey newJersey = new Jersey(nextId(),jersey.getName(),jersey.getCost(),jersey.getSize(),jersey.getIsHome(),jersey.getNumber());
            jerseys.put(newJersey.getId(), newJersey);
            save();
            return newJersey;
        }
    }

    @Override
    public Jersey[] getJerseys() throws IOException {
        synchronized(jerseys) {
            return getJerseysArray();
        }
    }

    @Override
    public Jersey[] findJersey(String text) throws IOException {
        return null;
    }

    @Override
    public Jersey updateJersey(Jersey jersey) throws IOException {
        return null;
    }

    @Override
    public boolean deleteJersey(int id) throws IOException {
        return false;
    } 
}
