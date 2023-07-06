package com.app.prosport.dbobjects.team;

import org.hibernate.boot.model.source.spi.Sortable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TeamRepo extends JpaRepository<Team, Integer> {
    public Optional<Team> findByTeamName(String name);

}
