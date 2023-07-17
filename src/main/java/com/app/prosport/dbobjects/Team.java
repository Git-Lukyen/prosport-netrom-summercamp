package com.app.prosport.dbobjects;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "Team name required")
    @Column
    private String teamName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate registrationDate;

    @OneToMany(mappedBy = "assignedTeam")
    private List<Player> players = new ArrayList<>();

    private Integer numberOfPlayers = players.size();

    @ManyToMany(mappedBy = "registeredTeams")
    private List<Competition> assignedComps;

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
