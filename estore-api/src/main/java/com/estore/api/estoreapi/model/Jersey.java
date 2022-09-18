package com.estore.api.estoreapi.model;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Jersey {
    private static final Logger LOG = Logger.getLogger(Jersey.class.getName());
    public enum Size {
        SMALL, MEDIUM, LARGE, XL;
    }

    @JsonProperty("id") private int id;
    @JsonProperty("name") private String name;
    @JsonProperty("cost") private float cost;
    @JsonProperty("size") private Size size;
    @JsonProperty("home") private boolean isHome;
    @JsonProperty("number") private int number;

    public Jersey(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("cost") float cost, @JsonProperty("size") Size size,
    @JsonProperty("home") boolean isHome, @JsonProperty("number") int number) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.size = size;
        this.isHome = isHome;
        this.number = number;
    }
}
