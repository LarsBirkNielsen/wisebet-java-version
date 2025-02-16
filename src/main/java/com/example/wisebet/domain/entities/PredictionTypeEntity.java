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
@Table(name="prediction_type")
public class PredictionTypeEntity {

    @Id
    @SequenceGenerator(name="prediction_type_generator", sequenceName = "prediction_type__id_seq", allocationSize = 1)
    @GeneratedValue(generator = "prediction_type_generator")
    @Column(unique = true)
    private Long id;

    @Column(name = "prediction_type_number", updatable = false)
    private UUID predictionTypeNumber;

    @Column(name = "prediction_type_name")
    private String predictionTypeName;

}

