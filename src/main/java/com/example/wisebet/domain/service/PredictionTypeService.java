package com.example.wisebet.domain.service;

import com.example.wisebet.domain.entities.PredictionTypeEntity;
import com.example.wisebet.domain.exception.InvalidDataException;
import com.example.wisebet.domain.models.PredictionTypeModel;
import com.example.wisebet.domain.repository.PredictionTypeRepository;
import com.example.wisebet.domain.validator.ModelValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PredictionTypeService {

    @Autowired
    private final PredictionTypeRepository predictionTypeRepository;

    public UUID createPredictionType(PredictionTypeModel predictionTypeModel) throws InvalidDataException {
        ModelValidator.validate(predictionTypeModel);
        PredictionTypeEntity predictionTypeEntity = createPredictionTypeEntity(predictionTypeModel);
        predictionTypeRepository.save(predictionTypeEntity);
        return predictionTypeEntity.getPredictionTypeNumber();
    }

    public UUID updatePredictionType(PredictionTypeModel predictionTypeModel) throws EntityNotFoundException, InvalidDataException{
        ModelValidator.validate(predictionTypeModel);
        Optional<PredictionTypeEntity> optionalPredictionTypeEntity = predictionTypeRepository.findByPredictionTypeNumber(predictionTypeModel.getPredictionTypeNumber());

        PredictionTypeEntity predictionTypeEntity = updatePredictionTypeEntity(predictionTypeModel, optionalPredictionTypeEntity.get());
        predictionTypeRepository.save(predictionTypeEntity);
        return predictionTypeEntity.getPredictionTypeNumber();
    }

    private PredictionTypeEntity createPredictionTypeEntity(PredictionTypeModel predictionTypeModel){
        PredictionTypeEntity predictionTypeEntity = new PredictionTypeEntity();
        predictionTypeEntity.setPredictionTypeNumber(UUID.randomUUID());
        updatePredictionTypeEntity(predictionTypeModel,predictionTypeEntity);

        return predictionTypeEntity;
    }

    private PredictionTypeEntity updatePredictionTypeEntity(PredictionTypeModel predictionTypeModel, PredictionTypeEntity predictionTypeEntity){
        predictionTypeEntity.setPredictionTypeName(predictionTypeModel.getPredictionTypeName());

        return predictionTypeEntity;
    }

    public UUID deletePredictionTypeEntity(UUID predictionTypeNumber)throws EntityNotFoundException {
        Optional<PredictionTypeEntity> optionalPredictionTypeEntity = predictionTypeRepository.findByPredictionTypeNumber(predictionTypeNumber);

        predictionTypeRepository.delete(optionalPredictionTypeEntity.get());
        return optionalPredictionTypeEntity.get().getPredictionTypeNumber();
    }

    public List<PredictionTypeEntity> getAllPredictionTypes(){
        return predictionTypeRepository.findAll();
    }
}
