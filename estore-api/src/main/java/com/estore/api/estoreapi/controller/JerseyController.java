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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estore.api.estoreapi.model.Jersey;
import com.estore.api.estoreapi.persistence.JerseyDAO;

@RestController
@RequestMapping("jerseys")
public class JerseyController {
    private JerseyDAO jerseyDAO;

    private static final Logger LOG = Logger.getLogger(JerseyController.class.getName());

    public JerseyController(JerseyDAO jerseyDAO){
        this.jerseyDAO = jerseyDAO;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jersey> getJersey(@PathVariable int id) {
        LOG.info("GET /jerseys/" + id);
        try {
            Jersey jersey = jerseyDAO.getJersey(id);
            if (jersey != null)
                return new ResponseEntity<Jersey>(jersey,HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<Jersey[]> getJerseys() {
        LOG.info("GET /jerseys");

        try {
            Jersey[] jerseys = jerseyDAO.getJerseys();
            if(jerseys != null)
            {
                return new ResponseEntity<Jersey[]>(jerseys, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<Jersey[]> searchJerseys(@RequestParam String name) {
        LOG.info("GET /jerseys/?name="+name);
        try {
            Jersey[] jerseys = jerseyDAO.findJersey(name);
            if (jerseys != null)
                return new ResponseEntity<Jersey[]>(jerseys,HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<Jersey> createJersey (@RequestBody Jersey jersey)
    {
        LOG.info("POST /jerseys " + jersey);

        try{
            if (jerseyDAO.createJersey(jersey) != null)
                return new ResponseEntity<Jersey>(jersey, HttpStatus.CREATED);
            else
                return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    public ResponseEntity<Jersey> updateJersey(@RequestBody Jersey jersey) {
        LOG.info("PUT /jerseys " + jersey);
        try {
            Jersey[] jerseys = jerseyDAO.getJerseys();
            for(int i =0;i<jerseys.length;i++){
                if(jerseys[i].equals(jersey)){
                    jerseyDAO.updateJersey(jersey);
                    return new ResponseEntity<>(HttpStatus.OK);
                }
            }
            return new ResponseEntity<Jersey>(jersey,HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Jersey> deleteJersey(@PathVariable int id) {
        LOG.info("DELETE /jerseys/" + id);
        try {
            Jersey[] jerseys = jerseyDAO.getJerseys();
            for(int i =0;i<jerseys.length;i++){
                if(jerseys[i].getId() == id){
                    jerseyDAO.deleteJersey(id);
                    return new ResponseEntity<>(HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
