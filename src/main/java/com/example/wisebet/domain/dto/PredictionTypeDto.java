package com.example.wisebet.domain.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class PredictionTypeDto {
    private UUID predictionTypeNumber;
    private String predictionTypeName;
}
