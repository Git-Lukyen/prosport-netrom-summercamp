package com.app.prosport.controllers;

import com.app.prosport.dbobjects.Competition;
import com.app.prosport.services.CompetitionService;
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

    @PostMapping(value = "comps/add/single", consumes = "application/json")
    public Competition addCompetition(@RequestBody Competition comp) {
        return compService.addCompetition(comp);
    }

    @PatchMapping(value = "comps/id/{id}")
    public void replaceCompContent(@PathVariable(value = "id") Integer ID, @RequestBody Competition newContent) {
        compService.replaceCompContent(ID, newContent);
    }

    @DeleteMapping(value = "comps/id/{id}")
    public void removeComp(@PathVariable(value = "id") Integer ID) {
        compService.removeCompetition(ID);
    }

}
