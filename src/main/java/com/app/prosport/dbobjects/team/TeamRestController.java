package com.app.prosport.dbobjects.team;

import com.app.prosport.dbobjects.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("prosport/")
public class TeamRestController {
    @Autowired
    TeamRepo teamRepo;

    @GetMapping(value = "teams")
    public List<Team> getAllTeams() {
        return teamRepo.findAll();
    }

    @GetMapping(value = "teams/id/{id}")
    public Optional<Team> getTeamByID(@PathVariable(value = "id") Integer ID) {
        return teamRepo.findById(ID);
    }

    @GetMapping(value = "teams/name/{name}")
    public Optional<Team> getTeamByName(@PathVariable(value = "name") String name) {
        return teamRepo.findByTeamName(name);
    }

    @GetMapping(value = "teams/sort/reg-date")
    public List<Team> sortByRegistrationDate() {
        return teamRepo.findAll(Sort.by("registrationDate"));
    }

    //TODO: make a filter between 2 dates and a sort by player count

    @PostMapping(value = "teams/add-team/single", consumes = "application/json")
    public Team addTeam(@RequestBody Team team) {
        return teamRepo.save(team);
    }

    @PostMapping(value = "teams/add-team/multiple", consumes = "application/json")
    public List<Team> addTeams(@RequestBody List<Team> teams) {
        return teamRepo.saveAll(teams);
    }

    @DeleteMapping(value = "teams/{id}")
    public void removeTeam(@PathVariable(value = "id") Integer ID) {
        teamRepo.deleteById(ID);
    }

    @DeleteMapping(value = "teams")
    public void clearTeams() {
        teamRepo.deleteAll();
    }

}
