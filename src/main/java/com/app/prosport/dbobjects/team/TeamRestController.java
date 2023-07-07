package com.app.prosport.dbobjects.team;

import com.app.prosport.dbobjects.player.Player;
import com.app.prosport.dbobjects.player.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("prosport/")
public class TeamRestController {
    @Autowired
    private TeamRepo teamRepo;
    @Autowired
    private PlayerRepo playerRepo;

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

    @GetMapping(value = "teams/sort/name")
    public List<Team> sortByTeamName() {
        return teamRepo.findAll(Sort.by("teamName"));
    }

    @GetMapping(value = "teams/filter/reg-date")
    public Optional<List<Team>> getAllFilterDate(@RequestParam("from") LocalDate from, @RequestParam("to") LocalDate to) {
        return teamRepo.findByRegistrationDateBetween(from, to, Sort.by("registrationDate"));
    }

    @PostMapping(value = "teams/add/single", consumes = "application/json")
    public Team addTeam(@RequestBody Team team) {
        return teamRepo.save(team);
    }

    @PostMapping(value = "teams/add/multiple", consumes = "application/json")
    public List<Team> addTeams(@RequestBody List<Team> teams) {
        return teamRepo.saveAll(teams);
    }

    @PostMapping(value = "teams/id/{id}/assign-player/{playerid}")
    public void assignPlayerToTeam(@PathVariable(value = "id") Integer ID, @PathVariable(value = "playerid") Integer playerID) {
        Team foundTeam = teamRepo.findById(ID).get();
        Player foundPlayer = playerRepo.findById(playerID).get();
        foundPlayer.setAssignedTeam(foundTeam);

        playerRepo.save(foundPlayer);
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
