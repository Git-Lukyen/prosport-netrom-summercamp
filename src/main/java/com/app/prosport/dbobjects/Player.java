package com.app.prosport.dbobjects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer playerID;

    @ManyToOne(optional = true)
    @JoinColumn(name = "teamid")
    @JsonIgnore
    private Team assignedTeam;

    private String firstName;
    private String lastName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate registrationDate;

    //Years, Centimeters, Kilograms
    private Integer age;
    private Integer weight;
    private Integer height;
}
