package com.bkikenski.ultimatefitness.model.enumerations;

import java.util.ArrayList;
import java.util.List;

public enum Exercises {
    //enter ratios in decimal format, not percent
    DEADLIFT, //https://exercisedb.p.rapidapi.com/exercises/exercise/0032
    DEADLIFT_CROSSFIT,
    PULL_UPS, //https://exercisedb.p.rapidapi.com/exercises/exercise/0651
    PULL_DOWN, //https://exercisedb.p.rapidapi.com/exercises/exercise/0197
    PULL_DOWN_CROSSFIT,
    BENT_OVER_ROW, //https://exercisedb.p.rapidapi.com/exercises/exercise/0027
    BENCH_PRESS, //https://exercisedb.p.rapidapi.com/exercises/exercise/0025
    BENCH_PRESS_CROSSFIT,
    INCLINE_BENCH_PRESS, //https://exercisedb.p.rapidapi.com/exercises/exercise/0047
    DECLINE_BENCH_PRESS, ////https://exercisedb.p.rapidapi.com/exercises/exercise/0033
    OVERHEAD_PRESS, //https://exercisedb.p.rapidapi.com/exercises/exercise/0426
    OVERHEAD_PRESS_CROSSFIT,
    LATERAL_RAISES, //https://exercisedb.p.rapidapi.com/exercises/exercise/0334
    FRONT_RAISES, //https://exercisedb.p.rapidapi.com/exercises/exercise/0310
    DUMBBELL_PRESS, //https://exercisedb.p.rapidapi.com/exercises/exercise/0405
    MILITARY_PRESS, //https://exercisedb.p.rapidapi.com/exercises/exercise/1457
    MILITARY_PRESS_CROSSFIT,
    SQUATS, //https://exercisedb.p.rapidapi.com/exercises/exercise/0043
    SQUATS_CROSSFIT,
    FRONT_SQUATS, //https://exercisedb.p.rapidapi.com/exercises/exercise/1370
    FRONT_SQUAT_CROSSFIT,
    HACK_SQUATS, //https://exercisedb.p.rapidapi.com/exercises/exercise/0046
    LEG_PRESS, //https://exercisedb.p.rapidapi.com/exercises/exercise/0739
    ROMANIAN_DEADLIFT, //https://exercisedb.p.rapidapi.com/exercises/exercise/0085
    SPLIT_SQUATS, //https://exercisedb.p.rapidapi.com/exercises/exercise/2368
    BICEP_CURLS, //https://exercisedb.p.rapidapi.com/exercises/exercise/1677
    TRICEPS_EXTENSION, //https://exercisedb.p.rapidapi.com/exercises/exercise/0061
    POWER_CLEAN, //https://exercisedb.p.rapidapi.com/exercises/exercise/0648
    POWER_CLEAN_CROSSFIT,
    CLEAN_AND_JERK, //https://exercisedb.p.rapidapi.com/exercises/exercise/0028
    CLEAN_AND_JERK_CROSSFIT,
    PUSH_UP, //https://exercisedb.p.rapidapi.com/exercises/exercise/0975
    SHRUG, //https://exercisedb.p.rapidapi.com/exercises/exercise/0095
    CALF_RAISE, //https://exercisedb.p.rapidapi.com/exercises/exercise/1372
    LEG_CURL, //https://exercisedb.p.rapidapi.com/exercises/exercise/0599
    LEG_EXTENSION, //https://exercisedb.p.rapidapi.com/exercises/exercise/0585
    RUN, //https://exercisedb.p.rapidapi.com/exercises/exercise/0685
    ABS,
    RUN_1K,
    RUN_3K,
    RUN_5K,
    RUN_10K,
    RUN_SPRINT_100_5SETS,
    RUN_SPRINT_200_5SETS
    ;

    private final List<Integer> ratioPerLevelMale;
    private final List<Integer> ratioPerLevelFemale;
    private final String rapidApiUrl;


    Exercises(String... exerciseConstants) {
        this.ratioPerLevelMale = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            this.ratioPerLevelMale.add(Integer.parseInt(exerciseConstants[i]));
        }

        this.ratioPerLevelFemale = new ArrayList<>();
        for (int i = 5; i < 10; i++) {
            this.ratioPerLevelFemale.add(Integer.parseInt(exerciseConstants[i]));
        }

        this.rapidApiUrl = exerciseConstants[10];
    }

    public List<Integer> getRatioPerLevelMale() {
        return this.ratioPerLevelMale;
    }

    public List<Integer> getRatioPerLevelFemale() {
        return this.ratioPerLevelFemale;
    }
}
