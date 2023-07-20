package com.app.prosport.dbobjects;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer compID;

    private String compName;
    private String compLocation;

    private LocalDate compStart;
    private LocalDate compEnd;

    private Integer numberOfTeams;
    private Integer numberOfGames;

    private String bracketData;

    @Transient
    private List<List<String>> bracketNames;

    @Transient
    private List<List<Integer>> bracketScores;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "comp_team", joinColumns = {@JoinColumn(name = "comp_id")}, inverseJoinColumns = {@JoinColumn(name = "team_id")})
    private List<Team> registeredTeams;

    @OneToMany(mappedBy = "assignedComp")
    private List<Game> scheduledGames;

    public Integer getNumberOfTeams() {
        if (registeredTeams == null)
            return 0;

        return registeredTeams.size();
    }

    public Integer getNumberOfGames() {
        if (scheduledGames == null)
            return 0;

        return scheduledGames.size();
    }

    public void addTeam(Team team) {
        registeredTeams.add(team);
    }

    public void removeTeam(Team team) {
        registeredTeams.remove(team);
    }

    public void clearTeams() {
        registeredTeams.clear();
    }

    public List<List<String>> getBracketNames() {
        List<List<String>> pairNames = new ArrayList<>();
        for (Game game : scheduledGames) {
            if (game.getPlayingTeams().size() == 2) {
                Team team1 = game.getPlayingTeams().get(0);
                Team team2 = game.getPlayingTeams().get(1);

                pairNames.add(Arrays.asList(team1.getTeamID() + " - " + team1.getTeamName(), team2.getTeamID() + " - " + team2.getTeamName()));
            }
        }

        return pairNames;
    }

    public List<List<Integer>> getBracketScores() {
        List<List<Integer>> pairScores = new ArrayList<>();
        for (Game game : scheduledGames) {
            pairScores.add(Arrays.asList(game.getScoreRed(), game.getScoreBlue()));
        }

        return pairScores;
    }

    public void clearGames() {
        scheduledGames.clear();
    }
}
