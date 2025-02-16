package com.example.wisebet.domain.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class TeamDto {
    private String teamName;
    private UUID teamNumber;
    private Boolean isActive;
    private UUID leagueNumber; // Add this field for the frontend to pass the selected league
}
