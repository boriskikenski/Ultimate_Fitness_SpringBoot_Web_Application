package com.bkikenski.ultimatefitness.model.dto;

import com.bkikenski.ultimatefitness.model.enumerations.MuscleGroup;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TrainingDTO {
    private List<MuscleGroup> forMuscleGroup;
    private int restTimeSeconds;
    private List<ExerciseTrainingPageDTO> exercises;
}
