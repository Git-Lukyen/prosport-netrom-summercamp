package com.app.prosport.dbobjects.player;

import com.app.prosport.dbobjects.team.Team;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Optional;

@Entity
@Table
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer playerID;

    @ManyToOne(optional = true)
    @JoinColumn(name = "teamid")
    private Team assignedTeam;

    private String firstName;
    private String lastName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate registrationDate;

    private Integer age;
    private Integer weight;
    private Integer height;

    //Getters
    public Integer getPlayerID() {
        return playerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getHeight() {
        return height;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public Integer getAssignedTeam() {
        if (assignedTeam == null) return -1;

        return assignedTeam.getTeamID();
    }

    //Setters
    public void setAssignedTeam(Team team) {
        assignedTeam = team;
    }
}
