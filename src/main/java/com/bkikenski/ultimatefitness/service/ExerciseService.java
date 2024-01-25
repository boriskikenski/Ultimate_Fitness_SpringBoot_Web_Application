package com.bkikenski.ultimatefitness.service;

import com.bkikenski.ultimatefitness.model.User;
import com.bkikenski.ultimatefitness.model.enumerations.ExercisesConstants;
import com.bkikenski.ultimatefitness.model.enumerations.Sex;

import java.util.List;

public interface ExerciseService {
    int getExerciseLevel(ExercisesConstants exercise, Sex sex, float ratio);
    void setExercisesInitialWeight(User userId, List<ExercisesConstants> neverDoneExercises);
    List<ExercisesConstants> getExercisesForUser(User user);
    List<ExercisesConstants> getNeverDoneExercises(List<ExercisesConstants> previousExercises, List<ExercisesConstants> exercisesConstantsForLevel);
}
