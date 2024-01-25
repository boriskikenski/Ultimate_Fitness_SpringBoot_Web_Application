package com.bkikenski.ultimatefitness.model.enumerations;

import java.util.ArrayList;
import java.util.List;

public enum ExercisesConstants {
    DEADLIFT("0.3", "0.7", "1", "1.5", "2.2", "0.2", "0.5", "0.7", "1", "1.2", "https://exercisedb.p.rapidapi.com/exercises/exercise/0032"),
    DEADLIFT_CROSSFIT("0.15", "0.35", "0.5", "0.75", "1", "0.1", "0.2", "0.3", "0.4", "0.5", "https://exercisedb.p.rapidapi.com/exercises/exercise/0032"),
    PULL_UPS("3", "6", "9", "12", "15", "1", "2", "5", "7", "9", "https://exercisedb.p.rapidapi.com/exercises/exercise/0651"),
    PULL_DOWN("0.3", "0.5", "0.7", "1", "1.3", "0.1", "0.3", "0.5", "0.7", "0.9", "https://exercisedb.p.rapidapi.com/exercises/exercise/0197"),
    PULL_DOWN_CROSSFIT("0.2", "0.35", "0.5", "0.65", "0.85", "0.15", "0.25", "0.35", "0.45", "0.65", "https://exercisedb.p.rapidapi.com/exercises/exercise/0197"),
    BENT_OVER_ROW("0.3", "0.6", "0.9", "1.2", "1.5", "0.1", "0.3", "0.5", "0.7", "0.9", "https://exercisedb.p.rapidapi.com/exercises/exercise/0027"),
    BENCH_PRESS("0.3", "0.6", "0.9", "1.3", "1.6", "0.2", "0.35", "0.5", "0.7", "1", "https://exercisedb.p.rapidapi.com/exercises/exercise/0025"),
    BENCH_PRESS_CROSSFIT("0.2", "0.4", "0.6", "0.8", "1", "0.1", "0.2", "0.4", "0.6", "0.8", "https://exercisedb.p.rapidapi.com/exercises/exercise/0025"),
    INCLINE_BENCH_PRESS("0.3", "0.6", "0.9", "1.3", "1.6", "0.2", "0.35", "0.5", "0.7", "1", "https://exercisedb.p.rapidapi.com/exercises/exercise/0047"),
    DECLINE_BENCH_PRESS("0.3", "0.6", "0.9", "1.3", "1.6", "0.2", "0.35", "0.5", "0.7", "1", "https://exercisedb.p.rapidapi.com/exercises/exercise/0033"),
    OVERHEAD_PRESS("0.3", "0.6", "0.9", "1.3", "1.6", "0.2", "0.35", "0.5", "0.7", "1", "https://exercisedb.p.rapidapi.com/exercises/exercise/0426"),
    OVERHEAD_PRESS_CROSSFIT("0.2", "0.4", "0.6", "0.8", "1", "0.1", "0.2", "0.4", "0.6", "0.8", "https://exercisedb.p.rapidapi.com/exercises/exercise/0426"),
    LATERAL_RAISES("0.05", "0.1", "0.15", "0.2", "0.25", "0.03", "0.05", "0.07", "0.1", "0.15", "https://exercisedb.p.rapidapi.com/exercises/exercise/0334"),
    FRONT_RAISES("0.05", "0.1", "0.15", "0.2", "0.25", "0.03", "0.05", "0.07", "0.1", "0.15", "https://exercisedb.p.rapidapi.com/exercises/exercise/0310"),
    DUMBBELL_PRESS("0.3", "0.6", "0.9", "1.3", "1.6", "0.2", "0.35", "0.5", "0.7", "1", "https://exercisedb.p.rapidapi.com/exercises/exercise/0405"),
    MILITARY_PRESS("0.3", "0.6", "0.9", "1.3", "1.6", "0.2", "0.35", "0.5", "0.7", "1", "https://exercisedb.p.rapidapi.com/exercises/exercise/1457"),
    MILITARY_PRESS_CROSSFIT("0.2", "0.4", "0.6", "0.8", "1", "0.1", "0.2", "0.4", "0.6", "0.8", "https://exercisedb.p.rapidapi.com/exercises/exercise/1457"),
    SQUATS("0.3", "0.7", "1", "1.5", "2.2", "0.2", "0.5", "0.7", "1", "1.2", "https://exercisedb.p.rapidapi.com/exercises/exercise/0043"),
    SQUATS_CROSSFIT("0.15", "0.35", "0.5", "0.75", "1", "0.1", "0.2", "0.3", "0.4", "0.5", "https://exercisedb.p.rapidapi.com/exercises/exercise/0043"),
    FRONT_SQUATS("0.3", "0.7", "1", "1.5", "2.2", "0.2", "0.5", "0.7", "1", "1.2", "https://exercisedb.p.rapidapi.com/exercises/exercise/1370"),
    FRONT_SQUAT_CROSSFIT("0.15", "0.35", "0.5", "0.75", "1", "0.1", "0.2", "0.3", "0.4", "0.5", "https://exercisedb.p.rapidapi.com/exercises/exercise/1370"),
    HACK_SQUATS("0.3", "0.6", "0.9", "1.3", "1.6", "0.2", "0.35", "0.5", "0.7", "1", "https://exercisedb.p.rapidapi.com/exercises/exercise/0046"),
    LEG_PRESS("0.5", "0.9", "1.5", "2", "2.5", "0.3", "0.6", "1", "1.2", "1.5", "https://exercisedb.p.rapidapi.com/exercises/exercise/0739"),
    ROMANIAN_DEADLIFT("0.3", "0.6", "0.9", "1.3", "1.6", "0.2", "0.35", "0.5", "0.7", "1", "https://exercisedb.p.rapidapi.com/exercises/exercise/0085"),
    SPLIT_SQUATS("0.1", "0.2", "0.3", "0.4", "0.5", "0.05", "1", "0.15", "0.2", "0.25", "https://exercisedb.p.rapidapi.com/exercises/exercise/2368"),
    BICEP_CURLS("0.1", "0.2", "0.3", "0.4", "0.5", "0.05", "1", "0.15", "0.2", "0.25", "https://exercisedb.p.rapidapi.com/exercises/exercise/1677"),
    TRICEPS_EXTENSION("0.1", "0.2", "0.3", "0.4", "0.5", "0.05", "1", "0.15", "0.2", "0.25", "https://exercisedb.p.rapidapi.com/exercises/exercise/0061"),
    POWER_CLEAN("0.3", "0.6", "0.9", "1.3", "1.6", "0.2", "0.35", "0.5", "0.7", "1", "https://exercisedb.p.rapidapi.com/exercises/exercise/0648"),
    POWER_CLEAN_CROSSFIT("0.15", "0.35", "0.5", "0.75", "1", "0.1", "0.2", "0.3", "0.4", "0.5", "https://exercisedb.p.rapidapi.com/exercises/exercise/0648"),
    CLEAN_AND_JERK("0.3", "0.6", "0.9", "1.3", "1.6", "0.2", "0.35", "0.5", "0.7", "1", "https://exercisedb.p.rapidapi.com/exercises/exercise/0028"),
    CLEAN_AND_JERK_CROSSFIT("0.15", "0.35", "0.5", "0.75", "1", "0.1", "0.2", "0.3", "0.4", "0.5", "https://exercisedb.p.rapidapi.com/exercises/exercise/0028"),
    PUSH_UP("5", "10", "20", "30", "50", "3", "7", "10", "15", "20", "https://exercisedb.p.rapidapi.com/exercises/exercise/0975"),
    SHRUG("0.3", "0.6", "0.9", "1.3", "1.6", "0.2", "0.35", "0.5", "0.7", "1", "https://exercisedb.p.rapidapi.com/exercises/exercise/0095"),
    CALF_RAISE("0.3", "0.6", "0.9", "1.3", "1.6", "0.2", "0.35", "0.5", "0.7", "1", "https://exercisedb.p.rapidapi.com/exercises/exercise/1372"),
    LEG_CURL("0.3", "0.6", "0.9", "1.3", "1.6", "0.2", "0.35", "0.5", "0.7", "1", "https://exercisedb.p.rapidapi.com/exercises/exercise/0599"),
    LEG_EXTENSION("0.3", "0.6", "0.9", "1.3", "1.6", "0.2", "0.35", "0.5", "0.7", "1", "https://exercisedb.p.rapidapi.com/exercises/exercise/0585"),
    RUN("1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "https://exercisedb.p.rapidapi.com/exercises/exercise/0685"),
    ABS("10", "20", "30", "40", "50", "10", "20", "30", "40", "50", "https://exercisedb.p.rapidapi.com/exercises/exercise/0274"),
    RUN_1K("1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "https://exercisedb.p.rapidapi.com/exercises/exercise/0685"),
    RUN_3K("1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "https://exercisedb.p.rapidapi.com/exercises/exercise/0685"),
    RUN_5K("1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "https://exercisedb.p.rapidapi.com/exercises/exercise/0685"),
    RUN_10K("1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "https://exercisedb.p.rapidapi.com/exercises/exercise/0685"),
    RUN_SPRINT_100_5SETS("1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "https://exercisedb.p.rapidapi.com/exercises/exercise/0685"),
    RUN_SPRINT_200_5SETS("1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "https://exercisedb.p.rapidapi.com/exercises/exercise/0685")
    ;

    private final List<Double> ratioPerLevelMale;
    private final List<Double> ratioPerLevelFemale;
    private final String rapidApiUrl;


    ExercisesConstants(String... exerciseConstants) {
        this.ratioPerLevelMale = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            this.ratioPerLevelMale.add(Double.parseDouble(exerciseConstants[i]));
        }

        this.ratioPerLevelFemale = new ArrayList<>();
        for (int i = 5; i < 10; i++) {
            this.ratioPerLevelFemale.add(Double.parseDouble(exerciseConstants[i]));
        }

        this.rapidApiUrl = exerciseConstants[10];
    }

    public List<Double> getRatioPerLevelMale() {
        return this.ratioPerLevelMale;
    }

    public List<Double> getRatioPerLevelFemale() {
        return this.ratioPerLevelFemale;
    }
}
