package com.app.prosport.services;

import com.app.prosport.dbobjects.Competition;
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

    public Competition addCompetition(Competition comp) {
        return compRepository.save(comp);
    }

    public void removeCompetition(Integer ID) {
        compRepository.deleteById(ID);
    }
}
