package com.app.prosport.controllers;

import com.app.prosport.dbobjects.Player;
import com.app.prosport.dbobjects.Team;
import com.app.prosport.services.TeamPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
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

    @GetMapping(value = "teams/in-comp/{id}")
    public List<Team> getTeamsInComp(@PathVariable(value = "id") Integer ID) {
        return teamPlayerService.getTeamsInComp(ID);
    }

    @GetMapping(value = "teams/not-in-comp/{id}")
    public List<Team> getTeamsNotInComp(@PathVariable(value = "id") Integer ID) {
        return teamPlayerService.getTeamsNotInComp(ID);
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
    public Optional<Player> assignPlayerToTeam(@PathVariable(value = "id") Integer ID, @PathVariable(value = "playerid") Integer playerID) {
        return teamPlayerService.assignPlayerToTeam(ID, playerID);
    }

    @PostMapping(value = "teams/assign-comp/{compid}")
    public void assignTeamToComp(@RequestBody List<Integer> teamIDs, @PathVariable(value = "compid") Integer compID) {
        for (Integer ID : teamIDs) {
            teamPlayerService.assignTeamToComp(ID, compID);
        }
    }

    @PatchMapping(value = "teams/id/{id}", consumes = "application/json")
    public void replaceTeamContent(@PathVariable(value = "id") Integer ID, @RequestBody Team newContent) {
        teamPlayerService.replaceTeamContent(ID, newContent);
    }

    @PatchMapping(value = "teams/id/{id}/unassign-comp/{compid}")
    public void unassignComp(@PathVariable(value = "id") Integer ID, @PathVariable(value = "compid") Integer compID) {
        teamPlayerService.unassignComp(ID, compID);
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
