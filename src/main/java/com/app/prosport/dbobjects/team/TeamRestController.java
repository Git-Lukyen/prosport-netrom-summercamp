package com.app.prosport.dbobjects.team;

import com.app.prosport.dbobjects.player.Player;
import com.app.prosport.dbobjects.services.TeamPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("prosport/")
public class TeamRestController {
    @Autowired
    private TeamPlayerService teamPlayerService;

    @GetMapping(value = "teams")
    public List<Team> getAllTeams() {
        return teamPlayerService.getAllTeams();
    }

    @GetMapping(value = "teams/id/{id}")
    public Optional<Team> findTeamByID(@PathVariable(value = "id") Integer ID) {
        return teamPlayerService.findTeamByID(ID);
    }

    @GetMapping(value = "teams/name/{name}")
    public Optional<Team> findTeamByName(@PathVariable(value = "name") String name) {
        return teamPlayerService.findTeamByName(name);
    }

    @GetMapping(value = "teams/sort/reg-date")
    public List<Team> sortByRegistrationDate() {
        return teamPlayerService.sortTeamsByRegistrationDate();
    }

    @GetMapping(value = "teams/sort/name")
    public List<Team> sortByTeamName() {
        return teamPlayerService.sortTeamsByTeamName();
    }

    @GetMapping(value = "teams/filter/reg-date")
    public Optional<List<Team>> filterByDate(@RequestParam("from") LocalDate from, @RequestParam("to") LocalDate to) {
        return teamPlayerService.filterTeamsByDate(from, to);
    }

    @PostMapping(value = "teams/add/single", consumes = "application/json")
    public Team addTeam(@RequestBody Team team) {
        return teamPlayerService.addTeam(team);
    }

    @PostMapping(value = "teams/add/multiple", consumes = "application/json")
    public List<Team> addTeams(@RequestBody List<Team> teams) {
        return teamPlayerService.addTeams(teams);
    }

    @PostMapping(value = "teams/id/{id}/assign-player/{playerid}")
    public Player assignPlayerToTeam(@PathVariable(value = "id") Integer ID, @PathVariable(value = "playerid") Integer playerID) {
        return teamPlayerService.assignPlayerToTeam(ID, playerID);
    }

    @DeleteMapping(value = "teams/{id}")
    public void removeTeam(@PathVariable(value = "id") Integer ID) {
        teamPlayerService.removeTeam(ID);
    }

    @DeleteMapping(value = "teams")
    public void clearTeams() {
        teamPlayerService.clearTeams();
    }

}
