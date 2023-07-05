package com.app.prosport.dbobjects.team;

import com.app.prosport.dbobjects.game.Game;
import com.app.prosport.dbobjects.player.Player;
import com.app.prosport.dbobjects.score.Score;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Entity
@Table
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teamID;

    private String teamName;

    @OneToMany(mappedBy = "assignedTeam")
    private List<Player> players = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "team_game", joinColumns = {@JoinColumn(name = "team_id")}, inverseJoinColumns = {@JoinColumn(name = "game_id")})
    private List<Game> registeredGames = new ArrayList<>();

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

    public void registerGame(Game game) {
        registeredGames.add(game);
    }

    public void unregisterGame(Game game) {
        registeredGames.remove(game);
    }
}
