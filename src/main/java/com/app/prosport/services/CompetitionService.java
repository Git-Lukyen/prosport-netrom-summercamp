package com.app.prosport.services;

import com.app.prosport.dbobjects.Competition;
import com.app.prosport.dbobjects.Game;
import com.app.prosport.dbobjects.Team;
import com.app.prosport.repositories.CompRepository;
import com.app.prosport.repositories.GameRepository;
import com.app.prosport.repositories.TeamRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.*;

@Service
@CrossOrigin(origins = "http://localhost:8080")
public class CompetitionService {
    @Autowired
    private CompRepository compRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private TeamPlayerService teamPlayerService;
    @Autowired
    private TeamRepository teamRepository;

    public List<Competition> getAllCompetitions() {
        return compRepository.findAll();
    }

    public Competition getCompetitionByID(Integer compID) {
        return compRepository.findById(compID).get();
    }

    public Competition addCompetition(Competition comp) {
        return compRepository.save(comp);
    }

    public void generateTeamsBracket(Integer compID) {
        Competition foundComp = compRepository.findById(compID).get();
        Vector<Team> registeredTeams = new Vector<>(teamPlayerService.getTeamsInComp(compID));
        Collections.shuffle(registeredTeams);

        List<Game> scheduledGames = foundComp.getScheduledGames();
        for (Game game : scheduledGames) {
            for (Team team : game.getPlayingTeams()) {
                team.removeGame(game);
                teamRepository.save(team);
            }

            game.clearTeams();
            game.setAssignedComp(null);
            gameRepository.delete(game);
        }
        foundComp.clearGames();
        compRepository.save(foundComp);

        if (!(registeredTeams.size() == 2 || registeredTeams.size() == 4 || registeredTeams.size() == 8))
            return;

        for (int i = 1; i < registeredTeams.size(); i += 2) {
            Game game = new Game();
            gameRepository.save(game);

            Team team1 = registeredTeams.get(i - 1);
            Team team2 = registeredTeams.get(i);

            game.setAssignedComp(foundComp);

            game.addTeam(team1);
            game.addTeam(team2);
            team1.addGame(game);
            team2.addGame(game);

            gameRepository.save(game);
            teamRepository.save(team1);
            teamRepository.save(team2);
        }
    }

    public void replaceCompGames(Integer compID, JsonNode newData) {
        List<List<String>> bracketNames = new ArrayList<>();
        List<List<Integer>> bracketScores = new ArrayList<>();

        JsonNode namesArray = newData.get("teams");

        for (JsonNode node : namesArray) {
            bracketNames.add(Arrays.asList(node.get(0).asText(), node.get(1).asText()));
        }

        JsonNode resultsArray = newData.get("results");


        for (JsonNode node : resultsArray) {
            bracketScores.add(Arrays.asList(node.get(0).asInt(), node.get(1).asInt()));
        }

        Competition foundComp = compRepository.findById(compID).get();

        List<Game> scheduledGames = foundComp.getScheduledGames();
        for (Game game : scheduledGames) {
            for (Team team : game.getPlayingTeams()) {
                team.removeGame(game);
                teamRepository.save(team);
            }

            game.clearTeams();
            game.setAssignedComp(null);
            gameRepository.delete(game);
        }
        foundComp.clearGames();
        compRepository.save(foundComp);


        Integer ID = Integer.parseInt(bracketNames.get(0).get(0).split(" ")[0]);

        for (int i = 0; i < bracketNames.size(); i++) {
            Game game = new Game();
            gameRepository.save(game);

            Integer teamOneID = Integer.parseInt(bracketNames.get(i).get(0).split(" ")[0]);
            Integer teamTwoID = Integer.parseInt(bracketNames.get(i).get(1).split(" ")[0]);

            Team teamOne = teamRepository.findById(teamOneID).get();
            Team teamTwo = teamRepository.findById(teamTwoID).get();

            try {
                game.setScoreRed(bracketScores.get(i).get(0));
                game.setScoreRed(bracketScores.get(i).get(1));
            } catch (IndexOutOfBoundsException exception) {

            }

            game.setAssignedComp(foundComp);

            game.addTeam(teamOne);
            game.addTeam(teamTwo);
            teamOne.addGame(game);
            teamTwo.addGame(game);

            gameRepository.save(game);
            teamRepository.save(teamOne);
            teamRepository.save(teamTwo);
        }

    }

    public List<Game> getCompetitionGames(Integer compID) {
        return compRepository.findById(compID).get().getScheduledGames();
    }

    public void replaceCompContent(Integer compID, Competition newContent) {
        Competition foundComp = compRepository.findById(compID).get();
        foundComp.setCompName(newContent.getCompName());
        foundComp.setCompLocation(newContent.getCompLocation());
        foundComp.setCompStart(newContent.getCompStart());

        compRepository.save(foundComp);
    }

    public void removeCompetition(Integer ID) {
        Competition foundComp = compRepository.findById(ID).get();

        List<Team> registeredTeams = foundComp.getRegisteredTeams();
        for (Team team : registeredTeams) {
            team.removeCompetition(foundComp);
        }

        foundComp.clearTeams();

        compRepository.deleteById(foundComp.getCompID());
    }
}
