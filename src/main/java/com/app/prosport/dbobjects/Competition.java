package com.app.prosport.dbobjects;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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

    public void clearTeams(){
        registeredTeams.clear();
    }

}
