package com.bkikenski.ultimatefitness.model.util;

import com.bkikenski.ultimatefitness.model.enumerations.Exercises;
import com.bkikenski.ultimatefitness.model.enumerations.MuscleGroup;

import java.util.List;

import static com.bkikenski.ultimatefitness.model.enumerations.Exercises.*;
import static com.bkikenski.ultimatefitness.model.enumerations.MuscleGroup.*;

public class TrainingUtils {

    public static final List<MuscleGroup> PULL_DAY_MUSCLE_GROUPS = List.of(BACK, BICEPS, FOREARM, TRAPS, MuscleGroup.ABS);
    public static final List<MuscleGroup> PUSH_DAY_MUSCLE_GROUPS = List.of(CHEST, SHOULDERS, TRICEPS);
    public static final List<MuscleGroup> LEG_DAY_MUSCLE_GROUPS = List.of(LEGS, CALF);
    public static final List<MuscleGroup> FULL_BODY = List.of(MuscleGroup.FULL_BODY);

    public static final List<Exercises> BODYBUILDING_BEGINNER_EXERCISES = List.of(
            PULL_UPS, PULL_DOWN, BENCH_PRESS, INCLINE_BENCH_PRESS, LATERAL_RAISES, DUMBBELL_PRESS, SQUATS,
            SPLIT_SQUATS, BICEP_CURLS, TRICEPS_EXTENSION, DECLINE_BENCH_PRESS, PUSH_UP, SHRUG, LEG_PRESS, FRONT_SQUATS,
            CALF_RAISE, LEG_EXTENSION, LEG_CURL);
    public static final List<Exercises> BODYBUILDING_BEGINNER_PUSH_EXERCISES = List.of(
            BENCH_PRESS);
    public static final List<Exercises> BODYBUILDING_ADDITIONAL_BEGINNER_PUSH_EXERCISES = List.of(
            INCLINE_BENCH_PRESS, LATERAL_RAISES, DUMBBELL_PRESS, TRICEPS_EXTENSION, DECLINE_BENCH_PRESS, PUSH_UP);
    public static final List<Exercises> BODYBUILDING_BEGINNER_PULL_EXERCISES = List.of(
            PULL_UPS);
    public static final List<Exercises> BODYBUILDING_ADDITIONAL_BEGINNER_PULL_EXERCISES = List.of(
            PULL_DOWN, BICEP_CURLS, LATERAL_RAISES, SHRUG);
    public static final List<Exercises> BODYBUILDING_BEGINNER_LEG_EXERCISES = List.of(
            SQUATS);
    public static final List<Exercises> BODYBUILDING_ADDITIONAL_BEGINNER_LEG_EXERCISES = List.of(
            LEG_PRESS, FRONT_SQUATS, SPLIT_SQUATS, CALF_RAISE, LEG_CURL, LEG_EXTENSION);

    public static final List<Exercises> BODYBUILDING_INTERMEDIATE_EXERCISES = List.of(
            PULL_UPS, PULL_DOWN, BENT_OVER_ROW, BENCH_PRESS, INCLINE_BENCH_PRESS, LATERAL_RAISES, FRONT_RAISES,
            DUMBBELL_PRESS, SQUATS, LEG_PRESS, ROMANIAN_DEADLIFT, SPLIT_SQUATS, BICEP_CURLS, TRICEPS_EXTENSION,
            PUSH_UP, DECLINE_BENCH_PRESS, SHRUG, CALF_RAISE, LEG_EXTENSION, LEG_CURL);
    public static final List<Exercises> BODYBUILDING_INTERMEDIATE_PUSH_EXERCISES = List.of(
            BENCH_PRESS, LATERAL_RAISES);
    public static final List<Exercises> BODYBUILDING_ADDITIONAL_INTERMEDIATE_PUSH_EXERCISES = List.of(
            INCLINE_BENCH_PRESS, FRONT_RAISES, DUMBBELL_PRESS, TRICEPS_EXTENSION, PUSH_UP, DECLINE_BENCH_PRESS);
    public static final List<Exercises> BODYBUILDING_INTERMEDIATE_PULL_EXERCISES = List.of(
            PULL_UPS, PULL_DOWN);
    public static final List<Exercises> BODYBUILDING_ADDITIONAL_INTERMEDIATE_PULL_EXERCISES = List.of(
            BENT_OVER_ROW, BICEP_CURLS, LATERAL_RAISES, SHRUG);
    public static final List<Exercises> BODYBUILDING_INTERMEDIATE_LEG_EXERCISES = List.of(
            SQUATS, LEG_EXTENSION, CALF_RAISE);
    public static final List<Exercises> BODYBUILDING_ADDITIONAL_INTERMEDIATE_LEG_EXERCISES = List.of(
            LEG_PRESS, ROMANIAN_DEADLIFT, SPLIT_SQUATS, FRONT_SQUATS, LEG_CURL);
    public static final List<Exercises> BODYBUILDING_INTERMEDIATE_FULL_EXERCISES = List.of(
        BENCH_PRESS, PULL_UPS, LEG_EXTENSION);
    public static final List<Exercises> BODYBUILDING_ADDITIONAL_INTERMEDIATE_FULL_EXERCISES = List.of(
            LATERAL_RAISES, BICEP_CURLS, SHRUG, FRONT_RAISES, TRICEPS_EXTENSION);

    public static final List<Exercises> BODYBUILDING_ADVANCED_EXERCISES = List.of(
            DEADLIFT, PULL_UPS, PULL_DOWN, BENT_OVER_ROW, BENCH_PRESS, INCLINE_BENCH_PRESS, OVERHEAD_PRESS,
            LATERAL_RAISES, FRONT_RAISES, DUMBBELL_PRESS, SQUATS, FRONT_SQUATS, HACK_SQUATS, LEG_PRESS,
            ROMANIAN_DEADLIFT, SPLIT_SQUATS, BICEP_CURLS, TRICEPS_EXTENSION, PUSH_UP, DECLINE_BENCH_PRESS,
            SHRUG, CALF_RAISE, LEG_EXTENSION, LEG_CURL);
    public static final List<Exercises> BODYBUILDING_ADVANCED_PUSH_EXERCISES = List.of(
            BENCH_PRESS, INCLINE_BENCH_PRESS, LATERAL_RAISES, DUMBBELL_PRESS);
    public static final List<Exercises> BODYBUILDING_ADDITIONAL_ADVANCED_PUSH_EXERCISES = List.of(
            OVERHEAD_PRESS, FRONT_RAISES, TRICEPS_EXTENSION, PUSH_UP, DECLINE_BENCH_PRESS);
    public static final List<Exercises> BODYBUILDING_ADVANCED_PULL_EXERCISES = List.of(
            DEADLIFT, PULL_UPS, BENT_OVER_ROW);
    public static final List<Exercises> BODYBUILDING_ADDITIONAL_ADVANCED_PULL_EXERCISES = List.of(
            PULL_DOWN, BICEP_CURLS, LATERAL_RAISES, SHRUG);
    public static final List<Exercises> BODYBUILDING_ADVANCED_LEG_EXERCISES = List.of(
            SQUATS, LEG_PRESS, LEG_EXTENSION, CALF_RAISE);
    public static final List<Exercises> BODYBUILDING_ADDITIONAL_ADVANCED_LEG_EXERCISES = List.of(
            FRONT_SQUATS, HACK_SQUATS, ROMANIAN_DEADLIFT, SPLIT_SQUATS, LEG_CURL);

    public static final List<Exercises> BODYBUILDING_EXPERT_EXERCISES = List.of(
            DEADLIFT, PULL_UPS, PULL_DOWN, BENT_OVER_ROW, BENCH_PRESS, INCLINE_BENCH_PRESS, OVERHEAD_PRESS,
            LATERAL_RAISES, FRONT_RAISES, DUMBBELL_PRESS, SQUATS, FRONT_SQUATS, HACK_SQUATS, LEG_PRESS,
            ROMANIAN_DEADLIFT, SPLIT_SQUATS, BICEP_CURLS, TRICEPS_EXTENSION, PUSH_UP, DECLINE_BENCH_PRESS,
            SHRUG, CALF_RAISE, LEG_EXTENSION, LEG_CURL);
    public static final List<Exercises> BODYBUILDING_EXPERT_PUSH_EXERCISES = List.of(
            BENCH_PRESS, INCLINE_BENCH_PRESS, OVERHEAD_PRESS, DECLINE_BENCH_PRESS);
    public static final List<Exercises> BODYBUILDING_ADDITIONAL_EXPERT_PUSH_EXERCISES = List.of(
            FRONT_RAISES, DUMBBELL_PRESS, TRICEPS_EXTENSION, PUSH_UP);
    public static final List<Exercises> BODYBUILDING_EXPERT_PULL_EXERCISES = List.of(
            DEADLIFT, PULL_UPS, BENT_OVER_ROW, BICEP_CURLS);
    public static final List<Exercises> BODYBUILDING_ADDITIONAL_EXPERT_PULL_EXERCISES = List.of(
            PULL_DOWN, LATERAL_RAISES, SHRUG);
    public static final List<Exercises> BODYBUILDING_EXPERT_LEG_EXERCISES = List.of(
            SQUATS, LEG_PRESS, CALF_RAISE, SPLIT_SQUATS);
    public static final List<Exercises> BODYBUILDING_ADDITIONAL_EXPERT_LEG_EXERCISES = List.of(
            FRONT_SQUATS, HACK_SQUATS, ROMANIAN_DEADLIFT, LEG_CURL, LEG_EXTENSION);

    public static final List<Exercises> BODYBUILDING_PROFESSIONAL_EXERCISES = List.of(
            DEADLIFT, PULL_UPS, PULL_DOWN, BENT_OVER_ROW, BENCH_PRESS, INCLINE_BENCH_PRESS, OVERHEAD_PRESS,
            FRONT_RAISES, DUMBBELL_PRESS, SQUATS, FRONT_SQUATS, HACK_SQUATS, LEG_PRESS, ROMANIAN_DEADLIFT,
            SPLIT_SQUATS, BICEP_CURLS, TRICEPS_EXTENSION, PUSH_UP, DECLINE_BENCH_PRESS, SHRUG, CALF_RAISE,
            LEG_EXTENSION, LEG_CURL);
    public static final List<Exercises> BODYBUILDING_PROFESSIONAL_PUSH_EXERCISES = List.of(
            BENCH_PRESS, INCLINE_BENCH_PRESS, OVERHEAD_PRESS, DUMBBELL_PRESS);
    public static final List<Exercises> BODYBUILDING_ADDITIONAL_PROFESSIONAL_PUSH_EXERCISES = List.of(
            LATERAL_RAISES, FRONT_RAISES, TRICEPS_EXTENSION, PUSH_UP, DECLINE_BENCH_PRESS);
    public static final List<Exercises> BODYBUILDING_PROFESSIONAL_PULL_EXERCISES = List.of(
            DEADLIFT, PULL_UPS, BENT_OVER_ROW, BICEP_CURLS);
    public static final List<Exercises> BODYBUILDING_ADDITIONAL_PROFESSIONAL_PULL_EXERCISES = List.of(
            PULL_DOWN, SHRUG, LATERAL_RAISES, FRONT_RAISES);
    public static final List<Exercises> BODYBUILDING_PROFESSIONAL_LEG_EXERCISES = List.of(
            SQUATS, LEG_PRESS, LEG_EXTENSION, SPLIT_SQUATS, CALF_RAISE);
    public static final List<Exercises> BODYBUILDING_ADDITIONAL_PROFESSIONAL_LEG_EXERCISES = List.of(
            FRONT_SQUATS, HACK_SQUATS, ROMANIAN_DEADLIFT, LEG_CURL);

    public static final List<Exercises> POWERLIFTING_BEGINNER_EXERCISES = List.of(
            DEADLIFT, PULL_UPS, PULL_DOWN, BENCH_PRESS, INCLINE_BENCH_PRESS, OVERHEAD_PRESS, SQUATS, LEG_PRESS,
            LATERAL_RAISES, BICEP_CURLS, LEG_EXTENSION);
    public static final List<Exercises> POWERLIFTING_BEGINNER_PUSH_EXERCISES = List.of(
            BENCH_PRESS);
    public static final List<Exercises> POWERLIFTING_ADDITIONAL_BEGINNER_PUSH_EXERCISES = List.of(
            INCLINE_BENCH_PRESS, OVERHEAD_PRESS, LATERAL_RAISES);
    public static final List<Exercises> POWERLIFTING_BEGINNER_PULL_EXERCISES = List.of(
            DEADLIFT, PULL_UPS);
    public static final List<Exercises> POWERLIFTING_ADDITIONAL_BEGINNER_PULL_EXERCISES = List.of(
            PULL_DOWN, LATERAL_RAISES, BICEP_CURLS);
    public static final List<Exercises> POWERLIFTING_BEGINNER_LEG_EXERCISES = List.of(
            SQUATS, CALF_RAISE);
    public static final List<Exercises> POWERLIFTING_ADDITIONAL_BEGINNER_LEG_EXERCISES = List.of(
            LEG_PRESS, LEG_EXTENSION);

    public static final List<Exercises> POWERLIFTING_INTERMEDIATE_EXERCISES = List.of(
            DEADLIFT, PULL_UPS, PULL_DOWN, BENCH_PRESS, INCLINE_BENCH_PRESS, OVERHEAD_PRESS, SQUATS, LEG_PRESS,
            POWER_CLEAN, CALF_RAISE, DUMBBELL_PRESS, LATERAL_RAISES, TRICEPS_EXTENSION, BICEP_CURLS,
            LEG_EXTENSION, LEG_CURL);
    public static final List<Exercises> POWERLIFTING_INTERMEDIATE_PUSH_EXERCISES = List.of(
            BENCH_PRESS, DUMBBELL_PRESS);
    public static final List<Exercises> POWERLIFTING_ADDITIONAL_INTERMEDIATE_PUSH_EXERCISES = List.of(
            INCLINE_BENCH_PRESS, OVERHEAD_PRESS, LATERAL_RAISES, TRICEPS_EXTENSION);
    public static final List<Exercises> POWERLIFTING_INTERMEDIATE_PULL_EXERCISES = List.of(
            DEADLIFT, PULL_UPS);
    public static final List<Exercises> POWERLIFTING_ADDITIONAL_INTERMEDIATE_PULL_EXERCISES = List.of(
            PULL_DOWN, POWER_CLEAN, BICEP_CURLS);
    public static final List<Exercises> POWERLIFTING_INTERMEDIATE_LEG_EXERCISES = List.of(
            SQUATS, CALF_RAISE);
    public static final List<Exercises> POWERLIFTING_ADDITIONAL_INTERMEDIATE_LEG_EXERCISES = List.of(
            LEG_PRESS, LEG_EXTENSION, LEG_CURL);

    public static final List<Exercises> POWERLIFTING_ADVANCED_EXERCISES = List.of(
            DEADLIFT, PULL_UPS, PULL_DOWN, BENT_OVER_ROW, BENCH_PRESS, INCLINE_BENCH_PRESS, OVERHEAD_PRESS,
            DUMBBELL_PRESS, MILITARY_PRESS, SQUATS, LEG_PRESS, ROMANIAN_DEADLIFT, POWER_CLEAN, CLEAN_AND_JERK,
            LEG_EXTENSION, LEG_CURL, BICEP_CURLS, TRICEPS_EXTENSION, PUSH_UP, PULL_UPS);
    public static final List<Exercises> POWERLIFTING_ADVANCED_PUSH_EXERCISES = List.of(
            BENCH_PRESS, INCLINE_BENCH_PRESS);
    public static final List<Exercises> POWERLIFTING_ADDITIONAL_ADVANCED_PUSH_EXERCISES = List.of(
            OVERHEAD_PRESS, DUMBBELL_PRESS, MILITARY_PRESS);
    public static final List<Exercises> POWERLIFTING_ADVANCED_PULL_EXERCISES = List.of(
            DEADLIFT, PULL_UPS, POWER_CLEAN);
    public static final List<Exercises> POWERLIFTING_ADDITIONAL_ADVANCED_PULL_EXERCISES = List.of(
            PULL_DOWN, BENT_OVER_ROW);
    public static final List<Exercises> POWERLIFTING_ADVANCED_LEG_EXERCISES = List.of(
            SQUATS, LEG_PRESS);
    public static final List<Exercises> POWERLIFTING_ADDITIONAL_ADVANCED_LEG_EXERCISES = List.of(
            ROMANIAN_DEADLIFT, LEG_EXTENSION, LEG_CURL);
    public static final List<Exercises> POWERLIFTING_ADVANCED_FULL_EXERCISES = List.of(
            CLEAN_AND_JERK, BICEP_CURLS, TRICEPS_EXTENSION);
    public static final List<Exercises> POWERLIFTING_ADDITIONAL_ADVANCED_FULL_EXERCISES = List.of(
            PUSH_UP, PULL_UPS, POWER_CLEAN);

    public static final List<Exercises> POWERLIFTING_EXPERT_EXERCISES = List.of(
            DEADLIFT, PULL_UPS, PULL_DOWN, BENT_OVER_ROW, BENCH_PRESS, INCLINE_BENCH_PRESS, OVERHEAD_PRESS,
            DUMBBELL_PRESS, MILITARY_PRESS, SQUATS, FRONT_SQUATS, LEG_PRESS, ROMANIAN_DEADLIFT, SPLIT_SQUATS,
            BICEP_CURLS, TRICEPS_EXTENSION, POWER_CLEAN, CLEAN_AND_JERK);
    public static final List<Exercises> POWERLIFTING_EXPERT_PUSH_EXERCISES = List.of(
            BENCH_PRESS, OVERHEAD_PRESS);
    public static final List<Exercises> POWERLIFTING_ADDITIONAL_EXPERT_PUSH_EXERCISES = List.of(
            INCLINE_BENCH_PRESS, DUMBBELL_PRESS, MILITARY_PRESS, TRICEPS_EXTENSION);
    public static final List<Exercises> POWERLIFTING_EXPERT_PULL_EXERCISES = List.of(
            DEADLIFT, PULL_UPS, BENT_OVER_ROW);
    public static final List<Exercises> POWERLIFTING_ADDITIONAL_EXPERT_PULL_EXERCISES = List.of(
            PULL_DOWN, BICEP_CURLS, POWER_CLEAN);
    public static final List<Exercises> POWERLIFTING_EXPERT_LEG_EXERCISES = List.of(
            SQUATS, LEG_PRESS);
    public static final List<Exercises> POWERLIFTING_ADDITIONAL_EXPERT_LEG_EXERCISES = List.of(
            FRONT_SQUATS, ROMANIAN_DEADLIFT, SPLIT_SQUATS, CLEAN_AND_JERK);

    public static final List<Exercises> POWERLIFTING_PROFESSIONAL_EXERCISES = List.of(
            DEADLIFT, PULL_UPS, PULL_DOWN, BENT_OVER_ROW, BENCH_PRESS, INCLINE_BENCH_PRESS, DUMBBELL_PRESS,
            MILITARY_PRESS, SQUATS, FRONT_SQUATS, LEG_PRESS, ROMANIAN_DEADLIFT, SPLIT_SQUATS, BICEP_CURLS,
            TRICEPS_EXTENSION, POWER_CLEAN, CLEAN_AND_JERK);
    public static final List<Exercises> POWERLIFTING_PROFESSIONAL_PUSH_EXERCISES = List.of(
            BENCH_PRESS, INCLINE_BENCH_PRESS, OVERHEAD_PRESS);
    public static final List<Exercises> POWERLIFTING_ADDITIONAL_PROFESSIONAL_PUSH_EXERCISES = List.of(
            DUMBBELL_PRESS, MILITARY_PRESS, TRICEPS_EXTENSION);
    public static final List<Exercises> POWERLIFTING_PROFESSIONAL_PULL_EXERCISES = List.of(
            DEADLIFT, PULL_UPS, BENT_OVER_ROW);
    public static final List<Exercises> POWERLIFTING_ADDITIONAL_PROFESSIONAL_PULL_EXERCISES = List.of(
            PULL_DOWN, BICEP_CURLS, POWER_CLEAN);
    public static final List<Exercises> POWERLIFTING_PROFESSIONAL_LEG_EXERCISES = List.of(
            SQUATS, LEG_PRESS, CLEAN_AND_JERK);
    public static final List<Exercises> POWERLIFTING_ADDITIONAL_PROFESSIONAL_LEG_EXERCISES = List.of(
            FRONT_SQUATS, ROMANIAN_DEADLIFT, SPLIT_SQUATS);

    public static final List<Exercises> CROSSFIT_BEGINNER_EXERCISES = List.of(
            POWER_CLEAN, PUSH_UP, PULL_UPS, LATERAL_RAISES, FRONT_RAISES, RUN, Exercises.ABS, CLEAN_AND_JERK,
            BICEP_CURLS, TRICEPS_EXTENSION, BENCH_PRESS_CROSSFIT, OVERHEAD_PRESS_CROSSFIT);
    public static final List<Exercises> CROSSFIT_BEGINNER_TRAINING_ONE = List.of(
            POWER_CLEAN, PUSH_UP, PULL_UPS);
    public static final List<Exercises> CROSSFIT_ADDITIONAL_BEGINNER_TRAINING_ONE = List.of(
            LATERAL_RAISES, FRONT_RAISES);
    public static final List<Exercises> CROSSFIT_BEGINNER_TRAINING_TWO = List.of(RUN, Exercises.ABS);
    public static final List<Exercises> CROSSFIT_ADDITIONAL_BEGINNER_TRAINING_TWO = List.of();
    public static final List<Exercises> CROSSFIT_BEGINNER_TRAINING_THREE = List.of(
            CLEAN_AND_JERK, BICEP_CURLS, TRICEPS_EXTENSION);
    public static final List<Exercises> CROSSFIT_ADDITIONAL_BEGINNER_TRAINING_THREE = List.of(
            BENCH_PRESS_CROSSFIT, OVERHEAD_PRESS_CROSSFIT);

    public static final List<Exercises> CROSSFIT_INTERMEDIATE_EXERCISES = List.of(
            POWER_CLEAN, PUSH_UP, PULL_UPS, LATERAL_RAISES, FRONT_RAISES, RUN, Exercises.ABS, CLEAN_AND_JERK,
            BICEP_CURLS, TRICEPS_EXTENSION, BENCH_PRESS_CROSSFIT, OVERHEAD_PRESS_CROSSFIT);
    public static final List<Exercises> CROSSFIT_INTERMEDIATE_TRAINING_ONE = List.of(
            POWER_CLEAN, PUSH_UP, PULL_UPS, Exercises.ABS);
    public static final List<Exercises> CROSSFIT_ADDITIONAL_INTERMEDIATE_TRAINING_ONE = List.of(
            LATERAL_RAISES, FRONT_RAISES);
    public static final List<Exercises> CROSSFIT_INTERMEDIATE_TRAINING_TWO = List.of(RUN, Exercises.ABS);
    public static final List<Exercises> CROSSFIT_ADDITIONAL_INTERMEDIATE_TRAINING_TWO = List.of();
    public static final List<Exercises> CROSSFIT_INTERMEDIATE_TRAINING_THREE = List.of(
            CLEAN_AND_JERK, BICEP_CURLS, TRICEPS_EXTENSION, RUN);
    public static final List<Exercises> CROSSFIT_ADDITIONAL_INTERMEDIATE_TRAINING_THREE = List.of(
            BENCH_PRESS_CROSSFIT, OVERHEAD_PRESS_CROSSFIT, Exercises.ABS);

    public static final List<Exercises> CROSSFIT_ADVANCED_EXERCISES = List.of(
            POWER_CLEAN, PUSH_UP, PULL_UPS, LATERAL_RAISES, FRONT_RAISES, RUN, Exercises.ABS, CLEAN_AND_JERK,
            BICEP_CURLS, TRICEPS_EXTENSION, BENCH_PRESS_CROSSFIT, OVERHEAD_PRESS_CROSSFIT, SQUATS_CROSSFIT);
    public static final List<Exercises> CROSSFIT_ADVANCED_TRAINING_ONE = List.of(
            POWER_CLEAN, PUSH_UP, PULL_UPS, Exercises.ABS);
    public static final List<Exercises> CROSSFIT_ADDITIONAL_ADVANCED_TRAINING_ONE = List.of(
            LATERAL_RAISES, FRONT_RAISES);
    public static final List<Exercises> CROSSFIT_ADVANCED_TRAINING_TWO = List.of(RUN, Exercises.ABS);
    public static final List<Exercises> CROSSFIT_ADDITIONAL_ADVANCED_TRAINING_TWO = List.of();
    public static final List<Exercises> CROSSFIT_ADVANCED_TRAINING_THREE = List.of(
            CLEAN_AND_JERK, BICEP_CURLS, TRICEPS_EXTENSION, RUN);
    public static final List<Exercises> CROSSFIT_ADDITIONAL_ADVANCED_TRAINING_THREE = List.of(
            BENCH_PRESS_CROSSFIT, OVERHEAD_PRESS_CROSSFIT, Exercises.ABS);
    public static final List<Exercises> CROSSFIT_ADVANCED_TRAINING_FOUR = List.of(
            BENCH_PRESS_CROSSFIT, SQUATS_CROSSFIT);
    public static final List<Exercises> CROSSFIT_ADDITIONAL_ADVANCED_TRAINING_FOUR = List.of(
            PULL_UPS, PUSH_UP, Exercises.ABS);

    public static final List<Exercises> CROSSFIT_EXPERT_EXERCISES = List.of(
            POWER_CLEAN, PUSH_UP, PULL_UPS, LATERAL_RAISES, FRONT_RAISES, RUN, Exercises.ABS, CLEAN_AND_JERK,
            BICEP_CURLS, TRICEPS_EXTENSION, BENCH_PRESS_CROSSFIT, OVERHEAD_PRESS_CROSSFIT, SQUATS_CROSSFIT,
            MILITARY_PRESS_CROSSFIT);
    public static final List<Exercises> CROSSFIT_EXPERT_TRAINING_ONE = List.of(
            POWER_CLEAN, PUSH_UP, PULL_UPS, Exercises.ABS);
    public static final List<Exercises> CROSSFIT_ADDITIONAL_EXPERT_TRAINING_ONE = List.of(
            LATERAL_RAISES, FRONT_RAISES);
    public static final List<Exercises> CROSSFIT_EXPERT_TRAINING_TWO = List.of(RUN, Exercises.ABS);
    public static final List<Exercises> CROSSFIT_ADDITIONAL_EXPERT_TRAINING_TWO = List.of();
    public static final List<Exercises> CROSSFIT_EXPERT_TRAINING_THREE = List.of(
            CLEAN_AND_JERK, BICEP_CURLS, TRICEPS_EXTENSION, RUN);
    public static final List<Exercises> CROSSFIT_ADDITIONAL_EXPERT_TRAINING_THREE = List.of(
            BENCH_PRESS_CROSSFIT, OVERHEAD_PRESS_CROSSFIT, Exercises.ABS);
    public static final List<Exercises> CROSSFIT_EXPERT_TRAINING_FOUR = List.of(
            BENCH_PRESS_CROSSFIT, SQUATS_CROSSFIT);
    public static final List<Exercises> CROSSFIT_ADDITIONAL_EXPERT_TRAINING_FOUR = List.of(
            PULL_UPS, PUSH_UP, Exercises.ABS);
    public static final List<Exercises> CROSSFIT_EXPERT_TRAINING_FIVE = List.of(
            MILITARY_PRESS_CROSSFIT, OVERHEAD_PRESS_CROSSFIT, RUN);
    public static final List<Exercises> CROSSFIT_ADDITIONAL_EXPERT_TRAINING_FIVE = List.of(Exercises.ABS);

    public static final List<Exercises> CROSSFIT_PROFESSIONAL_EXERCISES = List.of(
            POWER_CLEAN, PUSH_UP, PULL_UPS, LATERAL_RAISES, FRONT_RAISES, RUN, Exercises.ABS, CLEAN_AND_JERK,
            BICEP_CURLS, TRICEPS_EXTENSION, BENCH_PRESS_CROSSFIT, OVERHEAD_PRESS_CROSSFIT, SQUATS_CROSSFIT,
            MILITARY_PRESS_CROSSFIT);
    public static final List<Exercises> CROSSFIT_PROFESSIONAL_TRAINING_ONE = List.of(
            POWER_CLEAN, PUSH_UP, PULL_UPS, Exercises.ABS);
    public static final List<Exercises> CROSSFIT_ADDITIONAL_PROFESSIONAL_TRAINING_ONE = List.of(
            LATERAL_RAISES, FRONT_RAISES);
    public static final List<Exercises> CROSSFIT_PROFESSIONAL_TRAINING_TWO = List.of(RUN, Exercises.ABS);
    public static final List<Exercises> CROSSFIT_ADDITIONAL_PROFESSIONAL_TRAINING_TWO = List.of();
    public static final List<Exercises> CROSSFIT_PROFESSIONAL_TRAINING_THREE = List.of(
            CLEAN_AND_JERK, BICEP_CURLS, TRICEPS_EXTENSION, RUN);
    public static final List<Exercises> CROSSFIT_ADDITIONAL_PROFESSIONAL_TRAINING_THREE = List.of(
            BENCH_PRESS_CROSSFIT, OVERHEAD_PRESS_CROSSFIT, Exercises.ABS);
    public static final List<Exercises> CROSSFIT_PROFESSIONAL_TRAINING_FOUR = List.of(
            BENCH_PRESS_CROSSFIT, SQUATS_CROSSFIT);
    public static final List<Exercises> CROSSFIT_ADDITIONAL_PROFESSIONAL_TRAINING_FOUR = List.of(
            PULL_UPS, PUSH_UP, Exercises.ABS);
    public static final List<Exercises> CROSSFIT_PROFESSIONAL_TRAINING_FIVE = List.of(
            MILITARY_PRESS_CROSSFIT, OVERHEAD_PRESS_CROSSFIT, RUN);
    public static final List<Exercises> CROSSFIT_ADDITIONAL_PROFESSIONAL_TRAINING_FIVE = List.of(Exercises.ABS);
    public static final List<Exercises> CROSSFIT_PROFESSIONAL_TRAINING_SIX = List.of(RUN);
    public static final List<Exercises> CROSSFIT_ADDITIONAL_PROFESSIONAL_TRAINING_SIX = List.of(Exercises.ABS);

    public static final List<Exercises> OLYMPIC_BEGINNER_EXERCISES = List.of(
            DEADLIFT, BENCH_PRESS, OVERHEAD_PRESS, SQUATS, POWER_CLEAN, CLEAN_AND_JERK, BENT_OVER_ROW, RUN,
            BICEP_CURLS, TRICEPS_EXTENSION, PULL_UPS, SPLIT_SQUATS, CALF_RAISE);
    public static final List<Exercises> OLYMPIC_BEGINNER_TRAINING_ONE = List.of(
            POWER_CLEAN);
    public static final List<Exercises> OLYMPIC_ADDITIONAL_BEGINNER_TRAINING_ONE = List.of(
            DEADLIFT, BENT_OVER_ROW, RUN);
    public static final List<Exercises> OLYMPIC_BEGINNER_TRAINING_TWO = List.of(
            BENCH_PRESS, CLEAN_AND_JERK);
    public static final List<Exercises> OLYMPIC_ADDITIONAL_BEGINNER_TRAINING_TWO = List.of(
            OVERHEAD_PRESS, BICEP_CURLS, TRICEPS_EXTENSION);
    public static final List<Exercises> OLYMPIC_BEGINNER_TRAINING_THREE = List.of(
            SQUATS, PULL_UPS, RUN);
    public static final List<Exercises> OLYMPIC_ADDITIONAL_BEGINNER_TRAINING_THREE = List.of(
            SPLIT_SQUATS, CALF_RAISE);

    public static final List<Exercises> OLYMPIC_INTERMEDIATE_EXERCISES = List.of(
            DEADLIFT, BENCH_PRESS, OVERHEAD_PRESS, MILITARY_PRESS, SQUATS, CLEAN_AND_JERK, BENT_OVER_ROW, RUN,
            BICEP_CURLS, TRICEPS_EXTENSION, PULL_UPS, PUSH_UP, SPLIT_SQUATS, CALF_RAISE);
    public static final List<Exercises> OLYMPIC_INTERMEDIATE_TRAINING_ONE = List.of(
            POWER_CLEAN, MILITARY_PRESS);
    public static final List<Exercises> OLYMPIC_ADDITIONAL_INTERMEDIATE_TRAINING_ONE = List.of(
            DEADLIFT, BENT_OVER_ROW, RUN);
    public static final List<Exercises> OLYMPIC_INTERMEDIATE_TRAINING_TWO = List.of(
            BENCH_PRESS, CLEAN_AND_JERK);
    public static final List<Exercises> OLYMPIC_ADDITIONAL_INTERMEDIATE_TRAINING_TWO = List.of(
            OVERHEAD_PRESS, BICEP_CURLS, TRICEPS_EXTENSION);
    public static final List<Exercises> OLYMPIC_INTERMEDIATE_TRAINING_THREE = List.of(
            SQUATS, PULL_UPS, PUSH_UP, RUN);
    public static final List<Exercises> OLYMPIC_ADDITIONAL_INTERMEDIATE_TRAINING_THREE = List.of(
            SPLIT_SQUATS, CALF_RAISE);

    public static final List<Exercises> OLYMPIC_ADVANCED_EXERCISES = List.of(
            DEADLIFT, BENCH_PRESS, OVERHEAD_PRESS, DUMBBELL_PRESS, MILITARY_PRESS, SQUATS, POWER_CLEAN, CLEAN_AND_JERK,
            BENT_OVER_ROW, RUN, LATERAL_RAISES, PULL_UPS, PUSH_UP, FRONT_RAISES, SPLIT_SQUATS, CALF_RAISE);
    public static final List<Exercises> OLYMPIC_ADVANCED_TRAINING_ONE = List.of(
            DEADLIFT, POWER_CLEAN, MILITARY_PRESS);
    public static final List<Exercises> OLYMPIC_ADDITIONAL_ADVANCED_TRAINING_ONE = List.of(
            BENT_OVER_ROW, RUN, OVERHEAD_PRESS);
    public static final List<Exercises> OLYMPIC_ADVANCED_TRAINING_TWO = List.of(
            BENCH_PRESS, CLEAN_AND_JERK, LATERAL_RAISES);
    public static final List<Exercises> OLYMPIC_ADDITIONAL_ADVANCED_TRAINING_TWO = List.of(
            OVERHEAD_PRESS, DUMBBELL_PRESS, RUN);
    public static final List<Exercises> OLYMPIC_ADVANCED_TRAINING_THREE = List.of(
            SQUATS, PULL_UPS, PUSH_UP, RUN, FRONT_RAISES);
    public static final List<Exercises> OLYMPIC_ADDITIONAL_ADVANCED_TRAINING_THREE = List.of(
            SPLIT_SQUATS, CALF_RAISE);
    public static final List<Exercises> OLYMPIC_ADVANCED_TRAINING_FOUR = List.of(
            CLEAN_AND_JERK, RUN);
    public static final List<Exercises> OLYMPIC_ADDITIONAL_ADVANCED_TRAINING_FOUR = List.of(
            PULL_UPS, PUSH_UP);

    public static final List<Exercises> OLYMPIC_EXPERT_EXERCISES = List.of(
            DEADLIFT, BENCH_PRESS, OVERHEAD_PRESS, DUMBBELL_PRESS, MILITARY_PRESS, SQUATS, POWER_CLEAN, CLEAN_AND_JERK,
            RUN, BENT_OVER_ROW, LATERAL_RAISES, FRONT_RAISES, PULL_UPS, PUSH_UP, SPLIT_SQUATS, CALF_RAISE, BICEP_CURLS,
            TRICEPS_EXTENSION);
    public static final List<Exercises> OLYMPIC_EXPERT_TRAINING_ONE = List.of(
            DEADLIFT, POWER_CLEAN, MILITARY_PRESS, RUN);
    public static final List<Exercises> OLYMPIC_ADDITIONAL_EXPERT_TRAINING_ONE = List.of(
            BENT_OVER_ROW, RUN, OVERHEAD_PRESS);
    public static final List<Exercises> OLYMPIC_EXPERT_TRAINING_TWO = List.of(
            BENCH_PRESS, CLEAN_AND_JERK, LATERAL_RAISES, RUN);
    public static final List<Exercises> OLYMPIC_ADDITIONAL_EXPERT_TRAINING_TWO = List.of(
            DUMBBELL_PRESS, OVERHEAD_PRESS);
    public static final List<Exercises> OLYMPIC_EXPERT_TRAINING_THREE = List.of(
            SQUATS, MILITARY_PRESS, PULL_UPS, PUSH_UP, RUN, FRONT_RAISES);
    public static final List<Exercises> OLYMPIC_ADDITIONAL_EXPERT_TRAINING_THREE = List.of(
            SPLIT_SQUATS, CALF_RAISE);
    public static final List<Exercises> OLYMPIC_EXPERT_TRAINING_FOUR = List.of(
            CLEAN_AND_JERK, LATERAL_RAISES, FRONT_RAISES);
    public static final List<Exercises> OLYMPIC_ADDITIONAL_EXPERT_TRAINING_FOUR = List.of(RUN);
    public static final List<Exercises> OLYMPIC_EXPERT_TRAINING_FIVE = List.of(
            POWER_CLEAN, SPLIT_SQUATS, BICEP_CURLS, TRICEPS_EXTENSION);
    public static final List<Exercises> OLYMPIC_ADDITIONAL_EXPERT_TRAINING_FIVE = List.of(RUN);

    public static final List<Exercises> OLYMPIC_PROFESSIONAL_EXERCISES = List.of(
            DEADLIFT, BENCH_PRESS, OVERHEAD_PRESS, DUMBBELL_PRESS, MILITARY_PRESS, SQUATS, POWER_CLEAN, CLEAN_AND_JERK,
            RUN, BENT_OVER_ROW, LATERAL_RAISES, FRONT_RAISES, PULL_UPS, PUSH_UP, SPLIT_SQUATS, CALF_RAISE, BICEP_CURLS,
            TRICEPS_EXTENSION);
    public static final List<Exercises> OLYMPIC_PROFESSIONAL_TRAINING_ONE = List.of(
            DEADLIFT, POWER_CLEAN, MILITARY_PRESS, RUN);
    public static final List<Exercises> OLYMPIC_ADDITIONAL_PROFESSIONAL_TRAINING_ONE = List.of(
            BENT_OVER_ROW, RUN, OVERHEAD_PRESS);
    public static final List<Exercises> OLYMPIC_PROFESSIONAL_TRAINING_TWO = List.of(
            BENCH_PRESS, CLEAN_AND_JERK, LATERAL_RAISES, RUN);
    public static final List<Exercises> OLYMPIC_ADDITIONAL_PROFESSIONAL_TRAINING_TWO = List.of(
            DUMBBELL_PRESS, OVERHEAD_PRESS);
    public static final List<Exercises> OLYMPIC_PROFESSIONAL_TRAINING_THREE = List.of(
            SQUATS, MILITARY_PRESS, PULL_UPS, PUSH_UP, RUN, FRONT_RAISES);
    public static final List<Exercises> OLYMPIC_ADDITIONAL_PROFESSIONAL_TRAINING_THREE = List.of(
            SPLIT_SQUATS, CALF_RAISE);
    public static final List<Exercises> OLYMPIC_PROFESSIONAL_TRAINING_FOUR = List.of(
            CLEAN_AND_JERK, LATERAL_RAISES, FRONT_RAISES);
    public static final List<Exercises> OLYMPIC_ADDITIONAL_PROFESSIONAL_TRAINING_FOUR = List.of(RUN);
    public static final List<Exercises> OLYMPIC_PROFESSIONAL_TRAINING_FIVE = List.of(
            POWER_CLEAN, SPLIT_SQUATS, BICEP_CURLS, TRICEPS_EXTENSION);
    public static final List<Exercises> OLYMPIC_ADDITIONAL_PROFESSIONAL_TRAINING_FIVE = List.of(RUN);
    public static final List<Exercises> OLYMPIC_PROFESSIONAL_TRAINING_SIX = List.of(
            RUN);
    public static final List<Exercises> OLYMPIC_ADDITIONAL_PROFESSIONAL_TRAINING_SIX = List.of(
            LATERAL_RAISES, FRONT_RAISES, BICEP_CURLS, TRICEPS_EXTENSION);

    public static final List<Exercises> CARDIO_BEGINNER_TRAINING_ONE = List.of(RUN_3K);
    public static final List<Exercises> CARDIO_ADDITIONAL_BEGINNER_TRAINING_ONE = List.of(Exercises.ABS);
    public static final List<Exercises> CARDIO_BEGINNER_TRAINING_TWO = List.of(RUN_1K, Exercises.ABS);
    public static final List<Exercises> CARDIO_ADDITIONAL_BEGINNER_TRAINING_TWO = List.of();
    public static final List<Exercises> CARDIO_BEGINNER_TRAINING_THREE = List.of(RUN_3K);
    public static final List<Exercises> CARDIO_ADDITIONAL_BEGINNER_TRAINING_THREE = List.of(Exercises.ABS);

    public static final List<Exercises> CARDIO_INTERMEDIATE_TRAINING_ONE = List.of(RUN_3K, Exercises.ABS);
    public static final List<Exercises> CARDIO_ADDITIONAL_INTERMEDIATE_TRAINING_ONE = List.of();
    public static final List<Exercises> CARDIO_INTERMEDIATE_TRAINING_TWO = List.of(RUN_5K);
    public static final List<Exercises> CARDIO_ADDITIONAL_INTERMEDIATE_TRAINING_TWO = List.of();
    public static final List<Exercises> CARDIO_INTERMEDIATE_TRAINING_THREE = List.of(RUN_3K, Exercises.ABS);
    public static final List<Exercises> CARDIO_ADDITIONAL_INTERMEDIATE_TRAINING_THREE = List.of();

    public static final List<Exercises> CARDIO_ADVANCED_TRAINING_ONE = List.of(RUN_5K);
    public static final List<Exercises> CARDIO_ADDITIONAL_ADVANCED_TRAINING_ONE = List.of(
            RUN_SPRINT_100_5SETS, Exercises.ABS);
    public static final List<Exercises> CARDIO_ADVANCED_TRAINING_TWO = List.of(
            RUN_3K, RUN_SPRINT_100_5SETS, RUN_1K);
    public static final List<Exercises> CARDIO_ADDITIONAL_ADVANCED_TRAINING_TWO = List.of(Exercises.ABS);
    public static final List<Exercises> CARDIO_ADVANCED_TRAINING_THREE = List.of(RUN_3K);
    public static final List<Exercises> CARDIO_ADDITIONAL_ADVANCED_TRAINING_THREE = List.of();
    public static final List<Exercises> CARDIO_ADVANCED_TRAINING_FOUR = List.of(RUN_SPRINT_100_5SETS, Exercises.ABS);
    public static final List<Exercises> CARDIO_ADDITIONAL_ADVANCED_TRAINING_FOUR = List.of();

    public static final List<Exercises> CARDIO_EXPERT_TRAINING_ONE = List.of(
            RUN_5K, RUN_SPRINT_100_5SETS, RUN_1K);
    public static final List<Exercises> CARDIO_ADDITIONAL_EXPERT_TRAINING_ONE = List.of(Exercises.ABS);
    public static final List<Exercises> CARDIO_EXPERT_TRAINING_TWO = List.of(RUN_3K, Exercises.ABS);
    public static final List<Exercises> CARDIO_ADDITIONAL_EXPERT_TRAINING_TWO = List.of();
    public static final List<Exercises> CARDIO_EXPERT_TRAINING_THREE = List.of(RUN_10K);
    public static final List<Exercises> CARDIO_ADDITIONAL_EXPERT_TRAINING_THREE = List.of();
    public static final List<Exercises> CARDIO_EXPERT_TRAINING_FOUR = List.of(
            RUN_SPRINT_200_5SETS, RUN_3K, RUN_SPRINT_100_5SETS);
    public static final List<Exercises> CARDIO_ADDITIONAL_EXPERT_TRAINING_FOUR = List.of();
    public static final List<Exercises> CARDIO_EXPERT_TRAINING_FIVE = List.of(Exercises.ABS, RUN_SPRINT_100_5SETS);
    public static final List<Exercises> CARDIO_ADDITIONAL_EXPERT_TRAINING_FIVE = List.of();

    public static final List<Exercises> CARDIO_PROFESSIONAL_TRAINING_ONE = List.of(RUN_10K);
    public static final List<Exercises> CARDIO_ADDITIONAL_PROFESSIONAL_TRAINING_ONE = List.of();
    public static final List<Exercises> CARDIO_PROFESSIONAL_TRAINING_TWO = List.of(RUN_5K, Exercises.ABS);
    public static final List<Exercises> CARDIO_ADDITIONAL_PROFESSIONAL_TRAINING_TWO = List.of();
    public static final List<Exercises> CARDIO_PROFESSIONAL_TRAINING_THREE = List.of(RUN_10K, RUN_SPRINT_100_5SETS, RUN_1K);
    public static final List<Exercises> CARDIO_ADDITIONAL_PROFESSIONAL_TRAINING_THREE = List.of(Exercises.ABS);
    public static final List<Exercises> CARDIO_PROFESSIONAL_TRAINING_FOUR = List.of(RUN_5K, RUN_SPRINT_200_5SETS, RUN_3K);
    public static final List<Exercises> CARDIO_ADDITIONAL_PROFESSIONAL_TRAINING_FOUR = List.of(Exercises.ABS);
    public static final List<Exercises> CARDIO_PROFESSIONAL_TRAINING_FIVE = List.of(RUN_10K, RUN_SPRINT_200_5SETS);
    public static final List<Exercises> CARDIO_ADDITIONAL_PROFESSIONAL_TRAINING_FIVE = List.of(Exercises.ABS);
    public static final List<Exercises> CARDIO_PROFESSIONAL_TRAINING_SIX = List.of(
            Exercises.ABS, RUN_SPRINT_200_5SETS, Exercises.ABS);
    public static final List<Exercises> CARDIO_ADDITIONAL_PROFESSIONAL_TRAINING_SIX = List.of();
}