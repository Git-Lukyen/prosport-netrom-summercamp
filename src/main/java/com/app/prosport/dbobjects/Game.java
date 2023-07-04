package com.app.prosport.dbobjects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int gameID;

    private String locationName;

    public void setGameID(int ID) {
        gameID = ID;
    }

    public int getGameID() {
        return gameID;
    }

    public void setLocation(String name) {
        locationName = name;
    }

    public String getLocationName() {
        return locationName;
    }

}
