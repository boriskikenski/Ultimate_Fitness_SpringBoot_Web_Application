package com.bkikenski.ultimatefitness.model.dto;

import com.bkikenski.ultimatefitness.model.enumerations.Days;
import com.bkikenski.ultimatefitness.model.enumerations.MuscleGroup;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class TrainingPlanPageDTO {
    private  Long trainingId;
    private Days day;
    private List<MuscleGroup> trainingForMuscles;

    public String toStringMuscleGroups() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(trainingForMuscles.get(0));
        for (int i = 1; i < trainingForMuscles.size(); i++) {
            stringBuilder.append(", ");
            stringBuilder.append(trainingForMuscles.get(i));
        }
        return stringBuilder.toString();
    }
}
