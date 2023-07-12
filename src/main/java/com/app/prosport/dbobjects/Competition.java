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

    private String location;

    private LocalDate compStart;
    private LocalDate compEnd;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "comp_team", joinColumns = {@JoinColumn(name = "comp_id")}, inverseJoinColumns = {@JoinColumn(name = "team_id")})
    private List<Team> registeredTeams;

    @OneToMany(mappedBy = "assignedComp")
    private List<Game> scheduledGames;

}
