package com.example.wisebet.domain.repository;

import com.example.wisebet.domain.entities.LeagueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface LeagueRepository extends JpaRepository<LeagueEntity, Long> {
    List<LeagueEntity> findAll();
    Optional<LeagueEntity> findByLeagueNumber(UUID leagueNumber);
}
