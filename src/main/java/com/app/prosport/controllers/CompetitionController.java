package com.app.prosport.controllers;

import com.app.prosport.dbobjects.Competition;
import com.app.prosport.dbobjects.Game;
import com.app.prosport.services.CompetitionService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("prosport/")
public class CompetitionController {

    @Autowired
    private CompetitionService compService;

    @GetMapping(value = "comps")
    public List<Competition> getAllCompetitions() {
        return compService.getAllCompetitions();
    }

    @GetMapping(value = "comps/id/{id}")
    public Competition getCompetitionByID(@PathVariable(value = "id") Integer ID) {
        return compService.getCompetitionByID(ID);
    }

    @GetMapping(value = "comps/id/{id}/games")
    public List<Game> getCompetitionGames(@PathVariable(value = "id") Integer ID) {
        return compService.getCompetitionGames(ID);
    }

    @PostMapping(value = "comps/{id}/generate/bracket")
    public void generateTeamBrackets(@PathVariable(value = "id") Integer ID) {
        compService.generateTeamsBracket(ID);
    }

    @PostMapping(value = "comps/add/single", consumes = "application/json")
    public Competition addCompetition(@RequestBody Competition comp) {
        return compService.addCompetition(comp);
    }

    @PatchMapping(value = "comps/id/{id}")
    public void replaceCompContent(@PathVariable(value = "id") Integer ID, @RequestBody Competition newContent) {
        compService.replaceCompContent(ID, newContent);
    }

    @PatchMapping(value = "comps/id/{id}/games")
    public void replaceGames(@PathVariable(value = "id") Integer ID, @RequestBody JsonNode newData) {
        compService.replaceCompGames(ID, newData);
    }

    @DeleteMapping(value = "comps/id/{id}")
    public void removeComp(@PathVariable(value = "id") Integer ID) {
        compService.removeCompetition(ID);
    }

}
