package com.example.wisebet.domain.repository;

import com.example.wisebet.domain.entities.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface TeamRepository extends JpaRepository<TeamEntity, Long> {
    // Custom query methods can be declared here
    List<TeamEntity> findAll();
    Optional<TeamEntity> findByTeamNumber(UUID teamNumber);
}
