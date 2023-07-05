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
    private Integer teamID;

    private String teamName;

    @OneToMany(mappedBy = "assignedTeam")
    private List<Player> players = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "team_game", joinColumns = {@JoinColumn(name = "team_id")}, inverseJoinColumns = {@JoinColumn(name = "game_id")})
    private List<Game> registeredGames = new ArrayList<>();

    //ID getters-setters
    public void setTeamID(int ID) {
        teamID = ID;
    }

    public int getTeamID() {
        return teamID;
    }

    //TeamName getters-setters
    public void setTeamName(String name) {
        teamName = name;
    }

    public String getTeamName() {
        return teamName;
    }

    //Players getters-setters
    public Player getPlayer(int ID) {
        for (Player p : players)
            if (p.getPlayerID() == ID)
                return p;

        return null;
    }

    public Player getPlayer(String name) {
        for (Player p : players)
            if (p.getPlayerFirstName().equals(name) || p.getPlayerLastName().equals(name))
                return p;

        return null;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void addPlayers(List<Player> playersToAdd) {
        players.addAll(playersToAdd);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public void removePlayers(List<Player> players) {
        players.removeAll(players);
    }

    public void clearPlayers() {
        players.clear();
    }

    //RegisteredGamed getters-setters
    public void registerGame(Game game) {
        registeredGames.add(game);
    }

    public void registerGames(List<Game> games) {
        registeredGames.addAll(games);
    }

    public void unregisterGame(Game game) {
        registeredGames.remove(game);
    }

    public void unregisterGames(List<Game> games) {
        registeredGames.removeAll(games);
    }

    public void clearGames() {
        registeredGames.clear();
    }

}
