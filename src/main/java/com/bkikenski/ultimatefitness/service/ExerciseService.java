package com.bkikenski.ultimatefitness.service;

import com.bkikenski.ultimatefitness.model.User;
import com.bkikenski.ultimatefitness.model.enumerations.Exercises;
import com.bkikenski.ultimatefitness.model.enumerations.Sex;

import java.util.List;

public interface ExerciseService {
    int getExerciseLevel(Exercises exercise, Sex sex, float ratio);
    void setExercisesInitialWeight(User userId, List<Exercises> neverDoneExercises);
    List<Exercises> getExercisesForUser(User user);
    List<Exercises> getNeverDoneExercises(List<Exercises> previousExercises, List<Exercises> exercisesForLevel);
}
