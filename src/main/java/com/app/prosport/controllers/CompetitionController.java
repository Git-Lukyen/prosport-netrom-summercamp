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

    @PostMapping(value = "comps/add/single", consumes = "application/json")
    public Competition addCompetition(@RequestBody Competition comp) {
        return compService.addCompetition(comp);
    }

    @DeleteMapping(value = "comps/id/{id}")
    public void removeComp(@PathVariable(value = "id") Integer ID) {
        compService.removeCompetition(ID);
    }

}
