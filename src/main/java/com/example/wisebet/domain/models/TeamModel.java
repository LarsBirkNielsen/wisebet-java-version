package com.example.wisebet.domain.models;

import lombok.Data;

import java.util.UUID;

@Data
public class TeamModel {
    private UUID teamNumber;
    private String teamName;
    private Boolean isActive;
//    @NotEmpty
    private UUID leagueNumber; // Add this field to associate the team with a league
}
