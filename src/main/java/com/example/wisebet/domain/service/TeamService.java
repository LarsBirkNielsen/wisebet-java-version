package com.example.wisebet.domain.service;

import com.example.wisebet.domain.entities.LeagueEntity;
import com.example.wisebet.domain.entities.TeamEntity;
import com.example.wisebet.domain.exception.InvalidDataException;
import com.example.wisebet.domain.models.TeamModel;
import com.example.wisebet.domain.repository.LeagueRepository;
import com.example.wisebet.domain.repository.TeamRepository;
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
public class TeamService {

    private final TeamRepository teamRepository;
    private final LeagueRepository leagueRepository;

    public UUID createTeam(TeamModel teamModel) throws InvalidDataException {
        ModelValidator.validate(teamModel);
        TeamEntity teamEntity = createTeamEntity(teamModel);
        teamRepository.save(teamEntity);
        return teamEntity.getTeamNumber();
    }

    public UUID updateTeam(TeamModel teamModel) throws EntityNotFoundException, InvalidDataException {
        ModelValidator.validate(teamModel);
        TeamEntity teamEntity = teamRepository.findByTeamNumber(teamModel.getTeamNumber())
                .orElseThrow(() -> new EntityNotFoundException("Team not found"));
        updateTeamEntity(teamModel, teamEntity);
        teamRepository.save(teamEntity);
        return teamEntity.getTeamNumber();
    }

    public UUID deleteTeam(UUID teamNumber) throws EntityNotFoundException {
        TeamEntity teamEntity = teamRepository.findByTeamNumber(teamNumber)
                .orElseThrow(() -> new EntityNotFoundException("Team not found"));
        teamRepository.delete(teamEntity);
        return teamEntity.getTeamNumber();
    }

    public List<TeamEntity> getAllTeams() {
        return teamRepository.findAll();
    }

    // Helper method to create a new TeamEntity from a TeamModel
    private TeamEntity createTeamEntity(TeamModel teamModel) {
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setTeamNumber(UUID.randomUUID());
        updateTeamEntity(teamModel, teamEntity);
        return teamEntity;
    }

    // Helper method to update (or set) the properties of a TeamEntity based on a TeamModel.
    // It also retrieves and associates the corresponding LeagueEntity.
    private TeamEntity updateTeamEntity(TeamModel teamModel, TeamEntity teamEntity) {
        teamEntity.setTeamName(teamModel.getTeamName());
        teamEntity.setIsActive(teamModel.getIsActive());

        LeagueEntity leagueEntity = leagueRepository.findByLeagueNumber(teamModel.getLeagueNumber())
                .orElseThrow(() -> new EntityNotFoundException("League not found"));
        teamEntity.setLeague(leagueEntity);
        return teamEntity;
    }
}
