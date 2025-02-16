package com.example.wisebet.domain.service;

import com.example.wisebet.domain.entities.LeagueEntity;
import com.example.wisebet.domain.exception.InvalidDataException;
import com.example.wisebet.domain.models.LeagueModel;
import com.example.wisebet.domain.repository.LeagueRepository;
import com.example.wisebet.domain.validator.ModelValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class LeagueService {

    private final LeagueRepository leagueRepository;

    public UUID createLeague(LeagueModel leagueModel) throws InvalidDataException {
        ModelValidator.validate(leagueModel);
        LeagueEntity leagueEntity = createLeagueEntity(leagueModel);
        leagueRepository.save(leagueEntity);
        return leagueEntity.getLeagueNumber();
    }

    public UUID updateLeague(LeagueModel leagueModel) throws EntityNotFoundException, InvalidDataException {
        ModelValidator.validate(leagueModel);
        LeagueEntity leagueEntity = leagueRepository.findByLeagueNumber(leagueModel.getLeagueNumber())
                .orElseThrow(() -> new EntityNotFoundException("League not found"));

        updateLeagueEntity(leagueModel, leagueEntity);
        leagueRepository.save(leagueEntity);
        return leagueEntity.getLeagueNumber();
    }

    private LeagueEntity createLeagueEntity(LeagueModel leagueModel) {
        LeagueEntity leagueEntity = new LeagueEntity();
        leagueEntity.setLeagueNumber(UUID.randomUUID());
        updateLeagueEntity(leagueModel, leagueEntity);
        return leagueEntity;
    }

    private LeagueEntity updateLeagueEntity(LeagueModel leagueModel, LeagueEntity leagueEntity) {
        leagueEntity.setLeagueName(leagueModel.getLeagueName());
        leagueEntity.setCountry(leagueModel.getCountry());
        return leagueEntity;
    }

    public UUID deleteLeague(UUID leagueNumber) throws EntityNotFoundException {
        LeagueEntity leagueEntity = leagueRepository.findByLeagueNumber(leagueNumber)
                .orElseThrow(() -> new EntityNotFoundException("League not found"));

        leagueRepository.delete(leagueEntity);
        return leagueEntity.getLeagueNumber();
    }

    public List<LeagueEntity> getAllLeagues() {
        return leagueRepository.findAll();
    }
}
