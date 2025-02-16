package com.example.wisebet.domain.controller;

import com.example.wisebet.domain.controller.dto.ResponseUuidDto;
import com.example.wisebet.domain.dto.PredictionTypeDto;
import com.example.wisebet.domain.entities.PredictionTypeEntity;
import com.example.wisebet.domain.exception.InvalidDataException;
import com.example.wisebet.domain.models.PredictionTypeModel;
import com.example.wisebet.domain.service.PredictionTypeService;
import com.example.wisebet.domain.transformer.BaseTransformer;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "api/prediction-types", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
public class PredictionTypeController {

    private final PredictionTypeService predictionTypeService;
    private final BaseTransformer baseTransformer;

    @PostMapping
    public ResponseEntity<ResponseUuidDto> createPredictionType(@Valid @RequestBody @NotNull PredictionTypeDto predictionTypeDto) throws InvalidDataException {
        UUID predictionTypeResponse = predictionTypeService.createPredictionType(transformToPredictionTypeModel(predictionTypeDto));
        return ResponseEntity.ok(new ResponseUuidDto(predictionTypeResponse));
    }

    @GetMapping
    public ResponseEntity<List<PredictionTypeEntity>> getAllPredictionTypes() {
        List<PredictionTypeEntity> predictionTypes = predictionTypeService.getAllPredictionTypes();
        return ResponseEntity.ok(predictionTypes);
    }

    @PutMapping(value="/{predictionTypeNumber}")
    public ResponseEntity<ResponseUuidDto> updatePredictionType(@Valid @PathVariable @NotNull UUID predictionTypeNumber, @Valid @RequestBody @NotNull PredictionTypeDto predictionTypeDto) throws InvalidDataException, EntityNotFoundException {
        PredictionTypeModel predictionTypeModel = transformToPredictionTypeModel(predictionTypeDto);
        predictionTypeModel.setPredictionTypeNumber(predictionTypeNumber);
        UUID predictionTypeNumberResponse = predictionTypeService.updatePredictionType(predictionTypeModel);
        return ResponseEntity.ok(new ResponseUuidDto(predictionTypeNumberResponse));
    }

    @DeleteMapping("/{predictionTypeNumber}")
    public ResponseEntity<ResponseUuidDto> deletePredictionType(@PathVariable UUID predictionTypeNumber) {
        UUID deletedPredictionTypeNumber= predictionTypeService.deletePredictionTypeEntity(predictionTypeNumber);
        return ResponseEntity.ok(new ResponseUuidDto(deletedPredictionTypeNumber));
    }

    private PredictionTypeModel transformToPredictionTypeModel(PredictionTypeDto predictionTypeDto) {
        return baseTransformer.transform(predictionTypeDto, PredictionTypeModel.class);
    }
}
