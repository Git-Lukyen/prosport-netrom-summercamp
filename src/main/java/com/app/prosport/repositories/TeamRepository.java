package com.app.prosport.repositories;


import com.app.prosport.dbobjects.Team;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Integer> {
    public Optional<Team> findByTeamName(String name);
    public Optional<List<Team>> findByRegistrationDateBetween(LocalDate from, LocalDate to, Sort sort);
}
