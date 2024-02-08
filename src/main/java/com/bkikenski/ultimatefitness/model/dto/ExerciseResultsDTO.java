package com.bkikenski.ultimatefitness.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class ExerciseResultsDTO {
    private Float workingWeight;
    private List<Integer> reps;
}
