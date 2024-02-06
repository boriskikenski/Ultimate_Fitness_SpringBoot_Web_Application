package com.bkikenski.ultimatefitness.model.dto;

import com.bkikenski.ultimatefitness.model.enumerations.ExercisesConstants;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ExerciseDTO {
    private Long id;
    private ExercisesConstants exerciseName;
    private float personalRecord;
    private List<Integer> nextExceptedRepsPerSet;
    private float currentWorkingWeight;
    private RapidAPIResponseDTO restApiInfo;
}
