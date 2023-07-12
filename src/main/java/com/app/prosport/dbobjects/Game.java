package com.app.prosport.dbobjects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gameID;

    private Integer scoreRed;
    private Integer scoreBlue;

    @ManyToOne()
    @JoinColumn(name = "compid")
    @JsonIgnore
    private Competition assignedComp;

    @ManyToMany(mappedBy = "registeredGames")
    private List<Team> playingTeams = new ArrayList<>();


}
