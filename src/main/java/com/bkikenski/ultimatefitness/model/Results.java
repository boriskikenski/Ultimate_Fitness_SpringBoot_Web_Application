package com.bkikenski.ultimatefitness.model;

import com.bkikenski.ultimatefitness.model.enumerations.FitnessPlans;
import com.bkikenski.ultimatefitness.model.enumerations.MuscleGroup;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Entity
public class Results {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private FitnessPlans resultsForPlan;

    @ManyToOne
    private User user;

    private float weight;

    @ElementCollection
    private Map<MuscleGroup, Float> dimensionsPerBodyPart;

    @ManyToMany
    private List<Exercise> exercisesResults;
}
