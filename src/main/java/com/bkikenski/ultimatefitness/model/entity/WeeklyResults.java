package com.bkikenski.ultimatefitness.model.entity;

import com.bkikenski.ultimatefitness.model.enumerations.FitnessPlans;
import com.bkikenski.ultimatefitness.model.enumerations.MuscleGroup;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Entity
public class WeeklyResults {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private FitnessPlans resultsForPlan;

    @ManyToOne
    private User user;

    private float bodyWeight;

    @ElementCollection
    private Map<MuscleGroup, Float> dimensionsPerBodyPart;

    @OneToMany
    private List<Exercise> exercisesResults;

    public WeeklyResults() {
    }
}
