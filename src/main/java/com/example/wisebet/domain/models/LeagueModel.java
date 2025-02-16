package com.example.wisebet.domain.models;

import lombok.Data;

import java.util.UUID;

@Data
public class LeagueModel {
    //@NotEmpty(message = "League Number cannot be empty")
    private UUID leagueNumber;
    //@NotEmpty(message = "League Name cannot be empty")
    private String leagueName;
    //@NotEmpty(message = "Country cannot be empty")
    private String country;
}
