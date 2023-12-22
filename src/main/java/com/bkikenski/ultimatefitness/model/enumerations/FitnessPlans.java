package com.bkikenski.ultimatefitness.model.enumerations;

public enum FitnessPlans {
    BODYBUILDING("Bodybuilding"),
    POWERLIFTING("Powerlifting"),
    CROSSFIT("Crossfit"),
    OLYMPIC("Olympic"),
    CARDIO("Cardio"),
    BASIC_3_DAYS("Basic 3 days plan"),
    BASIC_4_DAYS("Basic 4 days plan"),
    BASIC_5_DAYS("Basic 5 days plan")
    ;

    private final String fitnessPlan;

    FitnessPlans(String fitnessPlan) {
        this.fitnessPlan = fitnessPlan;
    }

    public String getFitnessPlan() {
        return fitnessPlan;
    }
}
