package com.bkikenski.ultimatefitness.model;

import com.bkikenski.ultimatefitness.model.enumerations.ExercisePriorityLevel;
import com.bkikenski.ultimatefitness.model.enumerations.FitnessPlans;
import com.bkikenski.ultimatefitness.model.enumerations.MuscleGroup;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String exerciseName;

    private String description;

    @Enumerated(value = EnumType.STRING)
    private ExercisePriorityLevel exercisePriorityLevel;

    @ElementCollection
    @Enumerated(value = EnumType.STRING)
    private List<MuscleGroup> exerciseForMuscleGroup;

    @ElementCollection
    @Enumerated(value = EnumType.STRING)
    private List<FitnessPlans> exerciseForFitnessPlans;

    @ElementCollection
    private List<Integer> exerciseForUserLevels;

    private float personalRecord;

    @ElementCollection
    private List<Float> trainingWeights;

    //image
}
