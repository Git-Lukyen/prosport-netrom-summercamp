package com.app.prosport.dbobjects;

import jakarta.persistence.*;

import java.util.Vector;

@Entity
@Table
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int teamID;

    @Column
    private String teamName;

    @OneToMany(mappedBy = "team")
    private Vector<Player> players = new Vector<>();

    public void setTeamID(int ID) {
        teamID = ID;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamName(String name) {
        teamName = name;
    }

    public String getTeamName() {
        return teamName;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }
}
