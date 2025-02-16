package com.example.wisebet.domain.controller;

import com.example.wisebet.domain.controller.dto.ResponseUuidDto;
import com.example.wisebet.domain.dto.LeagueDto;
import com.example.wisebet.domain.exception.InvalidDataException;
import com.example.wisebet.domain.models.LeagueModel;
import com.example.wisebet.domain.service.LeagueService;
import com.example.wisebet.domain.transformer.BaseTransformer;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("http://localhost:3000")
//@RequestMapping(value = "/team", produces = MediaType.APPLICATION_JSON_VALUE,
//        consumes = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping(value = "api/leagues")
@RequiredArgsConstructor
public class LeagueController {

    private final LeagueService leagueService;
    private final BaseTransformer baseTransformer;

    @PostMapping
    public ResponseEntity<ResponseUuidDto> createLeague(@Valid @RequestBody @NotNull LeagueDto leagueDto) throws InvalidDataException {
        UUID teamResponse = leagueService.createLeague(transformToLeagueModel(leagueDto));
        return ResponseEntity.ok(new ResponseUuidDto(teamResponse));
    }

    @GetMapping()
    public ResponseEntity<List<LeagueModel>> getAllLeagues() {
        List<LeagueModel> league = leagueService.getAllLeagues().stream()
                .map(leagueEntity -> baseTransformer.transform(leagueEntity, LeagueModel.class))
                .toList();
        return ResponseEntity.ok(league);
    }

    @PutMapping(value="/{leagueNumber}")
    public ResponseEntity<ResponseUuidDto> updateLeague(@Valid @PathVariable @NotNull UUID leagueNumber, @Valid @RequestBody @NotNull LeagueDto leagueDto) throws InvalidDataException, EntityNotFoundException{
        LeagueModel leagueModel = transformToLeagueModel(leagueDto);
        leagueModel.setLeagueNumber(leagueNumber);
        UUID leagueNumberResponse = leagueService.updateLeague(leagueModel);
        return ResponseEntity.ok(new ResponseUuidDto(leagueNumberResponse));
    }

    @DeleteMapping(value="/{leagueNumber}")
    public ResponseEntity<ResponseUuidDto> deleteLeague(@PathVariable @NotNull UUID leagueNumber) throws EntityNotFoundException, InvalidDataException{
        UUID leagueResponse = leagueService.deleteLeague(leagueNumber);
        return ResponseEntity.ok(new ResponseUuidDto(leagueResponse));
    }

    private LeagueModel transformToLeagueModel(LeagueDto leagueDto){
        return baseTransformer.transform(leagueDto, LeagueModel.class);
    }
}
