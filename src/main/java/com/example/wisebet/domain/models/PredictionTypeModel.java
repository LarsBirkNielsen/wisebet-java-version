package com.example.wisebet.domain.models;

import lombok.Data;

import java.util.UUID;

@Data
public class PredictionTypeModel {
    //@NotEmpty(message = "League Number cannot be empty")
    private UUID predictionTypeNumber;
    //@NotEmpty(message = "League Name cannot be empty")
    private String predictionTypeName;
}
