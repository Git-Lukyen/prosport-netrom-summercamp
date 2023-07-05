package com.app.prosport.dbobjects.player;

import com.app.prosport.dbobjects.team.Team;
import jakarta.persistence.*;

@Entity
@Table
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int playerID;

    private String playerFirstName;
    private String playerLastName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamid")
    private Team assignedTeam;

    //ID getters-setters
    public void setPlayerID(int ID) {
        playerID = ID;
    }

    public int getPlayerID() {
        return playerID;
    }

    //Name getters-setters
    public void setPlayerFirstName(String name) {
        playerFirstName = name;
    }

    public void setPlayerLastName(String name) {
        playerLastName = name;
    }

    public String getPlayerFirstName() {
        return playerFirstName;
    }

    public String getPlayerLastName() {
        return playerLastName;
    }

}
