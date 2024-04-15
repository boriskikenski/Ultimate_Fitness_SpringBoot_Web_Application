package com.bkikenski.ultimatefitness.service;

import com.bkikenski.ultimatefitness.model.entity.Training;
import com.bkikenski.ultimatefitness.model.entity.User;
import com.bkikenski.ultimatefitness.model.enumerations.Days;
import com.bkikenski.ultimatefitness.model.enumerations.ExercisesConstants;
import com.bkikenski.ultimatefitness.model.enumerations.MuscleGroup;

import java.util.List;

public interface TrainingPlanService {
    void generateTrainingPlan(Long userId);
    void generateBodybuildingPlan(Long userId);
    void setWeightAndRepsBodybuilding(User user);
    void generatePowerliftingPlan(Long userId);
    void setWeightAndRepsPowerliftingAndOlympic(User user);
    void generateCrossfitPlan(Long userId);
    void setWeightAndRepsCrossfit(User user);
    void generateOlympicPlan(Long userId);
    void generateCardioPlan(Long userId);
    Training getTraining(Days day, List<MuscleGroup> muscleGroups, int restTime, List<ExercisesConstants> baseExercises,
                         List<ExercisesConstants> additionalExercises, User user);
    List<ExercisesConstants> getRandomExercises(int numberOfExercises, List<ExercisesConstants> exercisesConstantsFromList);
}
