package com.app.prosport.dbobjects.player;

import com.app.prosport.dbobjects.team.Team;
import jakarta.persistence.*;

@Entity
@Table
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int playerID;

    private String playerName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamid")
    private Team assignedTeam;

    public void setPlayerID(int ID) {
        playerID = ID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerName(String name) {
        playerName = name;
    }

    public String getPlayerName() {
        return playerName;
    }
}
