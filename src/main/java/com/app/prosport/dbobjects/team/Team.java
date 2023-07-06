package com.app.prosport.dbobjects.team;

import com.app.prosport.dbobjects.game.Game;
import com.app.prosport.dbobjects.player.Player;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teamID;

    private String teamName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate registrationDate;

    @OneToMany(mappedBy = "assignedTeam")
    private List<Player> players = new ArrayList<>();

    private Integer numberOfPlayers = players.size();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "team_game", joinColumns = {@JoinColumn(name = "team_id")}, inverseJoinColumns = {@JoinColumn(name = "game_id")})
    private List<Game> registeredGames = new ArrayList<>();
}
