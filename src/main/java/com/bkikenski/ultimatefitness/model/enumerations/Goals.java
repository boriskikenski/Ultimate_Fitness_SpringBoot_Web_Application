package com.bkikenski.ultimatefitness.model.enumerations;

public enum Goals {
    LOSE_WEIGHT("My goal is to lose weight"),
    GAIN_WEIGHT("My goal is to gain weight"),
    FITNESS("My goal is to have better overall fitness shape"),
    POWER("My goal is to have more power, be stronger"),
    STAMINA("My goal is to have more stamina"),
    HEALTH_AND_RECREATION("My goal is to improve health and recreate")
    ;

    private final String goal;

    Goals(String goal) {
        this.goal = goal;
    }

    public String getGoal() {
        return goal;
    }
}
