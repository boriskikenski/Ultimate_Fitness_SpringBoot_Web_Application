package com.bkikenski.ultimatefitness.model.dto;

import com.bkikenski.ultimatefitness.model.enumerations.Goals;
import com.bkikenski.ultimatefitness.model.enumerations.Sex;
import lombok.Data;

@Data
public class RegisterPersonalInfoDTO {
    private String name;
    private String surname;
    private String username;
    private String password;
    private String repeatPassword;
    private Sex sex;
    private int age;
    private int height;
    private int weight;
    private int steps;
    private Goals goal;
}
