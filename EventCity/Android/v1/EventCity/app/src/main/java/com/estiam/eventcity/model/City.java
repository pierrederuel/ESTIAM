package com.estiam.eventcity.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by ESTIAM on 20/06/2017.
 */

public class City implements Serializable {

    private Long id;
    private String name;
    private String image;
    private ArrayList<Event> events;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
}
