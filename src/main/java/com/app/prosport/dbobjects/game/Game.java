package com.app.prosport.dbobjects.game;

import com.app.prosport.dbobjects.score.Score;
import com.app.prosport.dbobjects.team.Team;
import jakarta.persistence.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gameID;

    private String gameName;
    private String locationName;

    @ManyToMany(mappedBy = "registeredGames")
    List<Team> playingTeams = new ArrayList<>();

    public void setGameID(int ID) {
        gameID = ID;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameName(String name) {
        gameName = name;
    }

    public String getGameName() {
        return gameName;
    }

    public void setLocation(String name) {
        locationName = name;
    }

    public String getLocationName() {
        return locationName;
    }

}
