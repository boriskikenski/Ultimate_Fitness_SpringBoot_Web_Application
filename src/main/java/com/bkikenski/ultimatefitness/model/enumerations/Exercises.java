package com.bkikenski.ultimatefitness.model.enumerations;

import java.util.ArrayList;
import java.util.List;

public enum Exercises {
    DEADLIFTS; //enter ratios in decimal format, not percent

    private final List<Integer> ratioPerLevelMale;
    private final List<Integer> ratioPerLevelFemale;

    Exercises(String... exerciseConstants) {
        this.ratioPerLevelMale = new ArrayList<>();
        for (int i = 0; i <= 4; i++) {
            this.ratioPerLevelMale.add(Integer.parseInt(exerciseConstants[i]));
        }

        this.ratioPerLevelFemale = new ArrayList<>();
        for (int i = 5; i <= 9; i++) {
            this.ratioPerLevelFemale.add(Integer.parseInt(exerciseConstants[i]));
        }
    }

    public List<Integer> getRatioPerLevelMale() {
        return this.ratioPerLevelMale;
    }

    public List<Integer> getRatioPerLevelFemale() {
        return this.ratioPerLevelFemale;
    }
}
