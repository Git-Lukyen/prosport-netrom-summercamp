package com.app.prosport.dbobjects;

import jakarta.persistence.*;

@Entity
@Table
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int playerID;

    @Column
    private String playerName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamid")
    private Team team;

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
