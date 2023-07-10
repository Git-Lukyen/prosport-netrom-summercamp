package com.app.prosport.repositories;

import com.app.prosport.dbobjects.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> {
}
