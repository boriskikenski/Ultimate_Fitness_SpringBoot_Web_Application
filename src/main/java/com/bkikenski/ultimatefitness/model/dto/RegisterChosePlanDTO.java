package com.bkikenski.ultimatefitness.model.dto;

import com.bkikenski.ultimatefitness.model.enumerations.FitnessPlans;
import lombok.Data;

@Data
public class RegisterChosePlanDTO {
    private Long userId;
    private FitnessPlans plan;
    private String action;
}
