package com.app.prosport.dbobjects.score;

import com.app.prosport.dbobjects.game.Game;
import com.app.prosport.dbobjects.team.Team;
import jakarta.persistence.*;

@Entity
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int scoreID;

}
