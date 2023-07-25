package com.app.prosport.repositories;

import com.app.prosport.dbobjects.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompRepository extends JpaRepository<Competition, Integer> {
}
