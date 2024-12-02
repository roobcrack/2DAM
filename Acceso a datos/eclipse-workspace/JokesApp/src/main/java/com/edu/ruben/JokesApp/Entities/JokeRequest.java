package com.edu.ruben.JokesApp.Entities;

import java.util.Map;

public class JokeRequest {
    private int id;
    private String setup;
    private String delivery;
    private String joke;
    private String language;
    private String category;
    private String type;
    private Map<String, Boolean> flags;
    private Map<String, int[]> idRange;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSetup() {
        return setup;
    }

    public void setSetup(String setup) {
        this.setup = setup;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Boolean> getFlags() {
        return flags;
    }

    public void setFlags(Map<String, Boolean> flags) {
        this.flags = flags;
    }

    public Map<String, int[]> getIdRange() {
        return idRange;
    }

    public void setIdRange(Map<String, int[]> idRange) {
        this.idRange = idRange;
    }
}
