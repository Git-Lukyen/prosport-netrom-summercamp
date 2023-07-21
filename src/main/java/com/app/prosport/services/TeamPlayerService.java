package com.app.prosport.services;

import com.app.prosport.dbobjects.Competition;
import com.app.prosport.dbobjects.Game;
import com.app.prosport.dbobjects.Player;
import com.app.prosport.dbobjects.Team;
import com.app.prosport.repositories.CompRepository;
import com.app.prosport.repositories.GameRepository;
import com.app.prosport.repositories.PlayerRepository;
import com.app.prosport.repositories.TeamRepository;
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

    @Autowired
    private CompRepository compRepository;

    @Autowired
    private GameRepository gameRepository;

    //Player Related
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Optional<Player> findPlayerByID(Integer ID) {
        return playerRepository.findById(ID);
    }

    public List<Player> findPlayersInTeam(Integer teamID) {
        Team foundTeam = teamRepository.findById(teamID).get();

        return foundTeam.getPlayers();
    }

    public List<Player> findPlayersNotInTeam(Integer teamID) {
        List<Player> teamPlayers = findPlayersInTeam(teamID);
        List<Player> allPlayers = getAllPlayers();

        allPlayers.removeAll(teamPlayers);
        return allPlayers;
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
        if (player.getRegistrationDate() == null)
            player.setRegistrationDate(LocalDate.now());

        return playerRepository.save(player);
    }

    public List<Player> addPlayers(List<Player> players) {
        return playerRepository.saveAll(players);
    }

    public void replacePlayerContent(Integer playerID, Player newContent) {
        Player foundPlayer = playerRepository.findById(playerID).get();

        foundPlayer.setRegistrationDate(newContent.getRegistrationDate());
        foundPlayer.setFirstName(newContent.getFirstName());
        foundPlayer.setLastName(newContent.getLastName());
        foundPlayer.setAge(newContent.getAge());
        foundPlayer.setHeight(newContent.getHeight());
        foundPlayer.setWeight(newContent.getWeight());

        playerRepository.save(foundPlayer);
    }

    public void unassignPlayer(Integer playerID) {
        Player foundPlayer = playerRepository.findById(playerID).get();
        foundPlayer.setAssignedTeam(null);
        playerRepository.save(foundPlayer);
    }

    public void deletePlayerById(Integer ID) {
        Player foundPlayer = playerRepository.findById(ID).get();
        foundPlayer.setAssignedTeam(null);
        playerRepository.delete(foundPlayer);

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

    public List<Team> getTeamsInComp(Integer compID) {
        Competition foundComp = compRepository.findById(compID).get();
        return foundComp.getRegisteredTeams();
    }

    public List<Team> getTeamsNotInComp(Integer compID) {
        List<Team> allTeams = getAllTeams();
        List<Team> teamsInComp = getTeamsInComp(compID);

        allTeams.removeAll(teamsInComp);
        return allTeams;
    }

    public Team addTeam(Team team) {
        if (team.getRegistrationDate() == null)
            team.setRegistrationDate(LocalDate.now());
        return teamRepository.save(team);
    }

    public List<Team> addTeams(List<Team> teams) {
        return teamRepository.saveAll(teams);
    }

    public Optional<Player> assignPlayerToTeam(Integer teamID, Integer playerID) {
        Team foundTeam = teamRepository.findById(teamID).get();
        Player foundPlayer = playerRepository.findById(playerID).get();

        if (foundTeam == null || foundPlayer == null)
            return null;

        foundPlayer.setAssignedTeam(foundTeam);
        foundPlayer.setAssignDate(LocalDate.now());

        playerRepository.save(foundPlayer);

        return Optional.of(foundPlayer);
    }

    public void assignTeamToComp(Integer teamID, Integer compID) {
        Team foundTeam = teamRepository.findById(teamID).get();
        Competition foundComp = compRepository.findById(compID).get();

        if (foundComp.getNumberOfTeams() >= 8)
            return;

        foundTeam.setAssignDate(LocalDate.now());

        foundComp.addTeam(foundTeam);
        foundTeam.addCompetition(foundComp);

        teamRepository.save(foundTeam);
        compRepository.save(foundComp);
    }

    public void replaceTeamContent(Integer teamID, Team newContent) {
        Team foundTeam = teamRepository.findById(teamID).get();

        foundTeam.setTeamName(newContent.getTeamName());
        foundTeam.setRegistrationDate(newContent.getRegistrationDate());

        teamRepository.save(foundTeam);
    }

    public void unassignComp(Integer teamID, Integer compID) {
        Team foundTeam = teamRepository.findById(teamID).get();
        Competition foundComp = compRepository.findById(compID).get();

        foundTeam.removeCompetition(foundComp);
        foundComp.removeTeam(foundTeam);

        teamRepository.save(foundTeam);
        compRepository.save(foundComp);
    }

    public void removeTeam(Integer ID) {

        Team foundTeam = teamRepository.findById(ID).get();

        List<Player> teamPlayers = foundTeam.getPlayers();

        for (Player player : teamPlayers)
            player.setAssignedTeam(null);

        List<Competition> competitions = foundTeam.getAssignedComps();
        for (Competition competition : competitions) {
            competition.removeTeam(foundTeam);
            compRepository.save(competition);
        }
        foundTeam.clearCompetitions();

        List<Game> games = foundTeam.getRegisteredGames();
        for (Game game : games) {
            game.removeTeam(foundTeam);
            gameRepository.save(game);
        }
        foundTeam.clearGames();

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
