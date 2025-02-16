package com.example.wisebet.domain.controller;

import com.example.wisebet.domain.controller.dto.ResponseUuidDto;
import com.example.wisebet.domain.dto.TeamDto;
import com.example.wisebet.domain.models.TeamModel;
import com.example.wisebet.domain.service.TeamService;
import com.example.wisebet.domain.transformer.BaseTransformer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/teams")
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;
    private final BaseTransformer baseTransformer;

    @PostMapping
    public ResponseEntity<ResponseUuidDto> createTeam(@Valid @RequestBody TeamDto teamDto) {
        TeamModel teamModel = baseTransformer.transform(teamDto, TeamModel.class);
        UUID teamNumber = teamService.createTeam(teamModel);
        return ResponseEntity.ok(new ResponseUuidDto(teamNumber));
    }

    @GetMapping
    public ResponseEntity<List<TeamModel>> getAllTeams() {
        List<TeamModel> teams = baseTransformer.transformList(teamService.getAllTeams(), TeamModel.class);
        return ResponseEntity.ok(teams);
    }

    @PutMapping("/{teamNumber}")
    public ResponseEntity<ResponseUuidDto> updateTeam(@PathVariable UUID teamNumber,
                                                      @Valid @RequestBody TeamDto teamDto) {
        TeamModel teamModel = baseTransformer.transform(teamDto, TeamModel.class);
        teamModel.setTeamNumber(teamNumber);
        UUID updatedTeamNumber = teamService.updateTeam(teamModel);
        return ResponseEntity.ok(new ResponseUuidDto(updatedTeamNumber));
    }

    @DeleteMapping("/{teamNumber}")
    public ResponseEntity<ResponseUuidDto> deleteTeam(@PathVariable UUID teamNumber) {
        UUID deletedTeamNumber = teamService.deleteTeam(teamNumber);
        return ResponseEntity.ok(new ResponseUuidDto(deletedTeamNumber));
    }
}
