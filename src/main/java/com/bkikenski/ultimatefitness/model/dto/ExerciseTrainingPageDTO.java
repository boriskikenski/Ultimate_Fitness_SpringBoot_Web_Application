package com.bkikenski.ultimatefitness.model.dto;

import com.bkikenski.ultimatefitness.model.enumerations.ExercisesConstants;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExerciseTrainingPageDTO {
    private Long id;
    private ExercisesConstants exerciseName;
}
