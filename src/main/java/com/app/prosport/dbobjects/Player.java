package com.app.prosport.dbobjects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotEmpty(message = "First name required")
    private String firstName;
    @NotEmpty(message = "Last name required")
    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate registrationDate;

    //Years, Centimeters, Kilograms
    @NotNull(message = "Age required")
    private Integer age;
    @NotNull(message = "Weight required")
    private Integer weight;
    @NotNull(message = "Height required")
    private Integer height;
}
