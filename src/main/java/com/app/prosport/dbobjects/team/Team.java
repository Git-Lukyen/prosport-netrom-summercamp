package com.app.prosport.dbobjects.team;

import com.app.prosport.dbobjects.game.Game;
import com.app.prosport.dbobjects.player.Player;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teamID;

    @Column
    private String teamName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate registrationDate;

    @OneToMany(mappedBy = "assignedTeam")
    private List<Player> players = new ArrayList<>();

    private Integer numberOfPlayers = players.size();
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "team_game", joinColumns = {@JoinColumn(name = "team_id")}, inverseJoinColumns = {@JoinColumn(name = "game_id")})
    private List<Game> registeredGames = new ArrayList<>();

    public Integer getNumberOfPlayers() {
        Integer len = players.size();
        if (len == null)
            return 0;

        return len;
    }
}
