package com.bkikenski.ultimatefitness.model;

import com.bkikenski.ultimatefitness.model.enumerations.ExercisePriorityLevel;
import com.bkikenski.ultimatefitness.model.enumerations.Exercises;
import com.bkikenski.ultimatefitness.model.enumerations.MuscleGroup;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Exercises exerciseName;

    private String description;

    @Enumerated(value = EnumType.STRING)
    private ExercisePriorityLevel exercisePriorityLevel; //TODO to be removed

    @ElementCollection
    @Enumerated(value = EnumType.STRING)
    private List<MuscleGroup> exerciseForMuscleGroup;

    @ElementCollection
    private List<Integer> exerciseForUserLevels; //TODO to be removed

    private float personalRecord;

    @ElementCollection
    private List<Float> previousWeights;

    @ElementCollection
    private List<Integer> lastTrainingRepsPerSet;

    @ElementCollection
    private List<Integer> nextExceptedRepsPerSet;

    private float currentWorkingWeight;

}
