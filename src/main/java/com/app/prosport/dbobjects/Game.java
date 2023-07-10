package com.app.prosport.dbobjects;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gameID;

    private String gameName;
    private String locationName;

    private Integer scoreRed;
    private Integer scoreBlue;

    @ManyToMany(mappedBy = "registeredGames")
    List<Team> playingTeams = new ArrayList<>();


}
