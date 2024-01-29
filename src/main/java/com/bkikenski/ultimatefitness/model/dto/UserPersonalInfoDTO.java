package com.bkikenski.ultimatefitness.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserPersonalInfoDTO {
    private String name;
    private String surname;
    private int age;
    private int height;
    private int dailySteps;
}
