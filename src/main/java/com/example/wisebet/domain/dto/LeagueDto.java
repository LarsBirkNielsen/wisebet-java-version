package com.example.wisebet.domain.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class LeagueDto {
    private UUID leagueNumber;
    private String leagueName;
    private String country;
}
