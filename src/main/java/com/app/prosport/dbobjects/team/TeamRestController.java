package com.app.prosport.dbobjects.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeamRestController {
    @Autowired
    TeamRepo teamRepo;

    @GetMapping(value = "prosport/teams")
    public List<Team> getTeams() {
        return teamRepo.findAll();
    }

    @PostMapping(value = "prosport/teams", consumes = "application/json")
    public Team addTeam(@RequestBody Team team) {
        return teamRepo.save(team);
    }
}
