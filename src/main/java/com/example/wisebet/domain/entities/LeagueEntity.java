package com.example.wisebet.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="league")
public class LeagueEntity {

    @Id
    @SequenceGenerator(name="league_generator", sequenceName = "league__id_seq", allocationSize = 1)
    @GeneratedValue(generator = "league_generator")
    @Column(unique = true)
    private Long id;

    @Column(name = "league_number", updatable = false)
    private UUID leagueNumber;

    @Column(nullable = false)
    private String leagueName;

    @Column(nullable = false)
    private String country;

}
