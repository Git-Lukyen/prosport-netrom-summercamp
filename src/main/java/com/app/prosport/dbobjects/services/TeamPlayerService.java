package com.app.prosport.dbobjects.services;

import com.app.prosport.dbobjects.player.Player;
import com.app.prosport.dbobjects.player.PlayerRepository;
import com.app.prosport.dbobjects.team.Team;
import com.app.prosport.dbobjects.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@CrossOrigin(origins = "http://localhost:8080")
public class TeamPlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    //Player Related
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Optional<Player> findPlayerByID(Integer ID) {
        return playerRepository.findById(ID);
    }

    public Optional<List<Player>> findPlayersByFirstName(String firstName) {
        return playerRepository.findAllByFirstName(firstName);
    }

    public Optional<List<Player>> findPlayersByLastName(String lastName) {
        return playerRepository.findAllByLastName(lastName);
    }

    public List<Player> sortPlayersByFirstName() {
        return playerRepository.findAll(Sort.by("firstName"));
    }

    public List<Player> sortPlayersByLastName() {
        return playerRepository.findAll(Sort.by("lastName"));
    }

    public List<Player> sortPlayersByRegistrationDate() {
        return playerRepository.findAll(Sort.by("registrationDate"));
    }

    public List<Player> sortPlayersByAge() {
        return playerRepository.findAll(Sort.by("age"));
    }

    public List<Player> sortPlayersByHeight() {
        return playerRepository.findAll(Sort.by("height"));
    }

    public List<Player> sortPlayersByWeight() {
        return playerRepository.findAll(Sort.by("weight"));
    }

    public Optional<List<Player>> filterPlayersByRegistrationDate(LocalDate from, LocalDate to) {
        return playerRepository.findByRegistrationDateBetween(from, to, Sort.by("registrationDate"));
    }

    public Optional<List<Player>> filterPlayersByAge(Integer from, Integer to) {
        return playerRepository.findByAgeBetween(from, to, Sort.by("age"));
    }

    public Optional<List<Player>> filterPlayersByHeight(Integer from, Integer to) {
        return playerRepository.findByHeightBetween(from, to, Sort.by("height"));
    }

    public Optional<List<Player>> filterPlayersByWeight(Integer from, Integer to) {
        return playerRepository.findByWeightBetween(from, to, Sort.by("weight"));
    }

    public Player addPlayer(Player player) {
        return playerRepository.save(player);
    }

    public List<Player> addPlayers(List<Player> players) {
        return playerRepository.saveAll(players);
    }

    public void deletePlayerById(Integer ID) {
        playerRepository.deleteById(ID);
    }

    public void deleteAllPlayers() {
        playerRepository.deleteAll();
    }

    //Team Related
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Optional<Team> findTeamByID(Integer ID) {
        return teamRepository.findById(ID);
    }

    public Optional<Team> findTeamByName(String name) {
        return teamRepository.findByTeamName(name);
    }

    public List<Team> sortTeamsByRegistrationDate() {
        return teamRepository.findAll(Sort.by("registrationDate"));
    }

    public List<Team> sortTeamsByTeamName() {
        return teamRepository.findAll(Sort.by("teamName"));
    }

    public Optional<List<Team>> filterTeamsByDate(LocalDate from, LocalDate to) {
        return teamRepository.findByRegistrationDateBetween(from, to, Sort.by("registrationDate"));
    }

    public Team addTeam(Team team) {
        return teamRepository.save(team);
    }

    public List<Team> addTeams(List<Team> teams) {
        return teamRepository.saveAll(teams);
    }

    public Player assignPlayerToTeam(Integer teamID, Integer playerID) {
        Team foundTeam = teamRepository.findById(teamID).get();
        Player foundPlayer = playerRepository.findById(playerID).get();
        foundPlayer.setAssignedTeam(foundTeam);

        playerRepository.save(foundPlayer);

        return foundPlayer;
    }

    public List<Player> assignPlayersToTeam(Integer teamID, List<Integer> playerIDs) {
        Team foundTeam = teamRepository.findById(teamID).get();
        List<Player> foundPlayers = playerRepository.findAllById(playerIDs);

        for (Player playerIterator : foundPlayers)
            playerIterator.setAssignedTeam(foundTeam);

        playerRepository.saveAll(foundPlayers);

        return foundPlayers;
    }

    public void removeTeam(Integer ID) {

        Team foundTeam = teamRepository.findById(ID).get();
        List<Player> teamPlayers = foundTeam.getPlayers();

        for (Player player : teamPlayers)
            player.setAssignedTeam(null);

        teamRepository.delete(foundTeam);
    }

    public void clearTeams() {
        List<Team> allTeams = teamRepository.findAll();
        for (Team team : allTeams)
            removeTeam(team.getTeamID());
    }

    @DeleteMapping(value = "prosport/teams-players")
    public void deleteTeamsAndPlayers() {
        playerRepository.deleteAll();
        clearTeams();
    }
}
