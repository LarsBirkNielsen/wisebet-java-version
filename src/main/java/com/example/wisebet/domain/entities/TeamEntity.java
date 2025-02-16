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
@Table(name="team")
public class TeamEntity {

    @Id
    @SequenceGenerator(name="team_generator", sequenceName = "team__id_seq", allocationSize = 1)
    @GeneratedValue(generator = "team_generator")
    @Column(unique = true)
    private Long id;

    @Column(name = "team_number", updatable = false)
    private UUID teamNumber;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne // Indicates many teams can belong to one league
    @JoinColumn(name = "league_id", referencedColumnName = "id") // foreign key in the team table
    private LeagueEntity league; // This is the relationship field

}

