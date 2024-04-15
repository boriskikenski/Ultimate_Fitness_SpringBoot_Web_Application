package com.bkikenski.ultimatefitness.model.entity;

import com.bkikenski.ultimatefitness.model.enumerations.ExercisesConstants;
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
    private ExercisesConstants exerciseName;

    private float personalRecord;

    @ElementCollection
    private List<Float> previousWeights;

    @ElementCollection
    private List<Integer> lastTrainingRepsPerSet;

    @ElementCollection
    private List<Integer> nextExceptedRepsPerSet;

    private float currentWorkingWeight;

}
