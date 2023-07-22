package com.bkikenski.ultimatefitness.model;

import com.bkikenski.ultimatefitness.model.enumerations.MuscleGroup;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int forUserLevel;

    @Enumerated(value = EnumType.STRING)
    private MuscleGroup forMuscleGroup;

    private int numberOfHighPriorityExercise;

    private int numberOfSetsPerExerciseForHighPriority;

    private int numberOfRepsPerSetForHighPriority;

    private int restTimeSecondsForHighPriority;

    private int numberOfMediumPriorityExercise;

    private int numberOfSetsPerExerciseForMediumPriority;

    private int numberOfRepsPerSetForMediumPriority;

    private int restTimeSecondsForMediumPriority;

    private int numberOfLowPriorityExercise;

    private int numberOfSetsPerExerciseForLowPriority;

    private int numberOfRepsPerSetForLowPriority;

    private int restTimeSecondsForLowPriority;

    @ManyToMany()
    private List<Exercise> exercises;

    private double trainingScore;
}
