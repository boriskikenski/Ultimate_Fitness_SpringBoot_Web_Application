package com.bkikenski.ultimatefitness.model.enumerations;

public enum FitnessPlans {
    BODYBUILDING("Bodybuilding"),
    POWERLIFTING("Powerlifting"),
    CROSSFIT("Crossfit"),
    OLYMPIC("Olympic"),
    CARDIO("Cardio")
    ;

    private final String fitnessPlan;

    FitnessPlans(String fitnessPlan) {
        this.fitnessPlan = fitnessPlan;
    }

    public String getFitnessPlan() {
        return fitnessPlan;
    }
}
