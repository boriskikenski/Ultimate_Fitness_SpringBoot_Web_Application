package com.bkikenski.ultimatefitness.service;

import com.bkikenski.ultimatefitness.model.Training;
import com.bkikenski.ultimatefitness.model.User;
import com.bkikenski.ultimatefitness.model.enumerations.Days;
import com.bkikenski.ultimatefitness.model.enumerations.Exercises;
import com.bkikenski.ultimatefitness.model.enumerations.MuscleGroup;

import java.util.List;

public interface TrainingPlanService {
    void generateTrainingPlan(Long userId);
    void generateBodybuildingPlan(Long userId);
    void setWeightAndRepsBodybuilding(User user);
    void generatePowerliftingPlan(Long userId);
    void setWeightAndRepsPowerlifting(User user);
    void generateCrossfitPlan(Long userId);
    void generateOlympicPlan(Long userId);
    void generateCardioPlan(Long userId);
    void generateBasic3(Long userId);
    void generateBasic4(Long userId);
    void generateBasic5(Long userId);
    Training getTraining(Days day, List<MuscleGroup> muscleGroups, int restTime, List<Exercises> baseExercises, List<Exercises> additionalExercises);
    List<Exercises> getRandomExercises(int numberOfExercises, List<Exercises> exercisesFromList);
}
