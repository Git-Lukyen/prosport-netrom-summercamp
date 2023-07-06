package com.app.prosport.dbobjects.player;

import com.app.prosport.dbobjects.team.Team;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int playerID;

    private String playerFirstName;
    private String playerLastName;

    private Integer playerAge;
    private Integer playerWeight;
    private Integer playerHeight;
    private LocalDate registrationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamid")
    private Team assignedTeam;

}
