package com.app.prosport.repositories;

import com.app.prosport.dbobjects.Player;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

    public Optional<List<Player>> findAllByFirstName(String firstName);

    public Optional<List<Player>> findAllByLastName(String lastName);

    public Optional<List<Player>> findByRegistrationDateBetween(LocalDate from, LocalDate to, Sort sort);

    public Optional<List<Player>> findByAgeBetween(Integer from, Integer to, Sort sort);

    public Optional<List<Player>> findByHeightBetween(Integer from, Integer to, Sort sort);

    public Optional<List<Player>> findByWeightBetween(Integer from, Integer to, Sort sort);
}
