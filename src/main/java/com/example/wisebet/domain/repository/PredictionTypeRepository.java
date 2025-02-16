package com.example.wisebet.domain.repository;

import com.example.wisebet.domain.entities.PredictionTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface PredictionTypeRepository extends JpaRepository<PredictionTypeEntity, Long> {
    // Custom query methods can be declared here
    List<PredictionTypeEntity> findAll();
    Optional<PredictionTypeEntity> findByPredictionTypeNumber(UUID id);
}
