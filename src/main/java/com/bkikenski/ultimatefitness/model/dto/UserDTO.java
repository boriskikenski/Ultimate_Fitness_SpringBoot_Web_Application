package com.bkikenski.ultimatefitness.model.dto;

import com.bkikenski.ultimatefitness.model.enumerations.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private String name;
    private String surname;
    private String username;
    private Sex sex;
    private int age;
    private int height;
    private float weight;
    private Goals goal;
    private FitnessPlans currentFitnessPlan;
    private int dailySteps;
}
