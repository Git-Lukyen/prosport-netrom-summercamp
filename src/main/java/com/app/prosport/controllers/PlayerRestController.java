package com.app.prosport.controllers;

import com.app.prosport.dbobjects.Player;
import com.app.prosport.services.TeamPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("prosport/")
public class PlayerRestController {
    @Autowired
    TeamPlayerService teamPlayerService;

    @GetMapping(value = "players")
    public List<Player> getAllPlayers() {
        return teamPlayerService.getAllPlayers();
    }

    @GetMapping(value = "players/id/{id}")
    public Optional<Player> findPlayerByID(@PathVariable(value = "id") Integer ID) {
        return teamPlayerService.findPlayerByID(ID);
    }

    @GetMapping(value = "players/in-team/{id}")
    public List<Player> findPlayersInTeam(@PathVariable(value = "id") Integer ID) {
        return teamPlayerService.findPlayersInTeam(ID);
    }

    @GetMapping(value = "players/not-in-team/{id}")
    public List<Player> findPlayersNotInTeam(@PathVariable(value = "id") Integer ID) {
        return teamPlayerService.findPlayersNotInTeam(ID);
    }

    @GetMapping(value = "players/first-name/{name}")
    public Optional<List<Player>> findPlayersByFirstName(@PathVariable(value = "name") String name) {
        return teamPlayerService.findPlayersByFirstName(name);
    }

    @GetMapping(value = "players/last-name/{name}")
    public Optional<List<Player>> findPlayersByLastName(@PathVariable(value = "name") String name) {
        return teamPlayerService.findPlayersByLastName(name);
    }

    @GetMapping(value = "players/sort/first-name")
    public List<Player> sortByFirstName() {
        return teamPlayerService.sortPlayersByFirstName();
    }

    @GetMapping(value = "players/sort/last-name")
    public List<Player> sortByLastName() {
        return teamPlayerService.sortPlayersByLastName();
    }

    @GetMapping(value = "players/sort/reg-date")
    public List<Player> sortByRegistrationDate() {
        return teamPlayerService.sortPlayersByRegistrationDate();
    }

    @GetMapping(value = "players/sort/age")
    public List<Player> sortByAge() {
        return teamPlayerService.sortPlayersByAge();
    }

    @GetMapping(value = "players/sort/height")
    public List<Player> sortByHeight() {
        return teamPlayerService.sortPlayersByHeight();
    }

    @GetMapping(value = "players/sort/weight")
    public List<Player> sortByWeight() {
        return teamPlayerService.sortPlayersByWeight();
    }

    @GetMapping(value = "players/filter/reg-date")
    public Optional<List<Player>> filterByRegistrationDate(@RequestParam("from") LocalDate from, @RequestParam("to") LocalDate to) {
        return teamPlayerService.filterPlayersByRegistrationDate(from, to);
    }

    @GetMapping(value = "players/filter/age")
    public Optional<List<Player>> filterByAge(@RequestParam("from") Integer from, @RequestParam("to") Integer to) {
        return teamPlayerService.filterPlayersByAge(from, to);
    }

    @GetMapping(value = "players/filter/height")
    public Optional<List<Player>> filterByHeight(@RequestParam("from") Integer from, @RequestParam("to") Integer to) {
        return teamPlayerService.filterPlayersByHeight(from, to);
    }

    @GetMapping(value = "players/filter/weight")
    public Optional<List<Player>> filterByWeight(@RequestParam("from") Integer from, @RequestParam("to") Integer to) {
        return teamPlayerService.filterPlayersByWeight(from, to);
    }

    @PostMapping(value = "players/add/single", consumes = "application/json")
    public Player addPlayer(@RequestBody Player player) {
        return teamPlayerService.addPlayer(player);
    }

    @PostMapping(value = "players/add/multiple", consumes = "application/json")
    public List<Player> addPlayers(@RequestBody List<Player> players) {
        return teamPlayerService.addPlayers(players);
    }

    @PostMapping(value = "players/id/{id}/assign-team/{teamid}")
    public Optional<Player> assignPlayerToTeam(@PathVariable(value = "id") Integer playerID, @PathVariable(value = "teamid") Integer ID) {
        return teamPlayerService.assignPlayerToTeam(ID, playerID);
    }

    @PatchMapping(value = "players/id/{id}/unassign-team")
    public void unassignTeam(@PathVariable(value = "id") Integer playerID) {
        teamPlayerService.unassignPlayer(playerID);
    }

    @PatchMapping(value = "players/id/{id}", consumes = "application/json")
    public void replacePlayerContent(@PathVariable(value = "id") Integer ID, @RequestBody Player newContent) {
        teamPlayerService.replacePlayerContent(ID, newContent);
    }

    @DeleteMapping(value = "players/{id}")
    public void deleteByID(@PathVariable(value = "id") Integer ID) {
        teamPlayerService.deletePlayerById(ID);
    }

    @DeleteMapping(value = "players")
    public void deleteAllPlayers() {
        teamPlayerService.deleteAllPlayers();
    }

}
