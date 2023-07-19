package com.app.prosport.services;

import com.app.prosport.dbobjects.Competition;
import com.app.prosport.dbobjects.Team;
import com.app.prosport.repositories.CompRepository;
import com.app.prosport.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service
@CrossOrigin(origins = "http://localhost:8080")
public class CompetitionService {
    @Autowired
    private CompRepository compRepository;
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
