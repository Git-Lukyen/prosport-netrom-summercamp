package com.app.prosport.dbobjects.game;

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
    private Integer gameID;

    private String gameName;
    private String locationName;

    private Integer scoreRed;
    private Integer scoreBlue;

    @ManyToMany(mappedBy = "registeredGames")
    List<Team> playingTeams = new ArrayList<>();


}
