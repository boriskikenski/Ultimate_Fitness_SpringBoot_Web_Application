package com.bkikenski.ultimatefitness.service.impl;

import com.bkikenski.ultimatefitness.model.Exercise;
import com.bkikenski.ultimatefitness.model.WeeklyResults;
import com.bkikenski.ultimatefitness.model.User;
import com.bkikenski.ultimatefitness.model.enumerations.ExercisesConstants;
import com.bkikenski.ultimatefitness.model.enumerations.Sex;
import com.bkikenski.ultimatefitness.repository.UserRepository;
import com.bkikenski.ultimatefitness.service.ExerciseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseServiceImplementation implements ExerciseService {
    private final UserRepository userRepository;

    public ExerciseServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public int getExerciseLevel(ExercisesConstants exercise, Sex sex, float ratio) {
        int exerciseLevel = -1;
        List<Double> exerciseRatiosPerLevel = new ArrayList<>();
        switch (sex) {
            case MALE -> exerciseRatiosPerLevel = exercise.getRatioPerLevelMale();
            case FEMALE -> exerciseRatiosPerLevel = exercise.getRatioPerLevelFemale();
        }

        for (int i = 0; exerciseLevel == -1; i++) {
            if (ratio < exerciseRatiosPerLevel.get(i))
                exerciseLevel = i;
            else if (i == 4)
                exerciseLevel = i;
        }

        return exerciseLevel;
    }

    @Override
    public void setExercisesInitialWeight(User user, List<ExercisesConstants> neverDoneExercises) {
        Sex userSex = user.getSex();
        WeeklyResults userLastResult = user.getResults().get(user.getResults().size() - 1);
        float userWeight = userLastResult.getBodyWeight();
        List<Exercise> userLastResultExercises = userLastResult.getExercisesResults();
        for (ExercisesConstants exercise: neverDoneExercises) {
            userLastResultExercises.add(Exercise.builder()
                    .exerciseName(exercise)
                    .personalRecord(0)
                    .previousWeights(new ArrayList<>())
                    .lastTrainingRepsPerSet(new ArrayList<>())
                    .nextExceptedRepsPerSet(new ArrayList<>())
                    .currentWorkingWeight(userSex == Sex.MALE ?
                            (float) (exercise.getRatioPerLevelMale().get(0) - 0.15) * userWeight :
                            (float) (exercise.getRatioPerLevelFemale().get(0) - 0.15) * userWeight)
                    .build());
        }
        userRepository.save(user);
    }

    @Override
    public List<ExercisesConstants> getExercisesForUser(User user) {
        List<WeeklyResults> userResults = user.getResults();
        List<Exercise> exercises = userResults.get(userResults.size() - 1).getExercisesResults();
        return exercises.stream()
                .map(Exercise::getExerciseName)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExercisesConstants> getNeverDoneExercises(List<ExercisesConstants> previousExercises, List<ExercisesConstants> exercisesConstantsForLevel) {
        List<ExercisesConstants> exercisesConstantsList = new ArrayList<>(exercisesConstantsForLevel);
        exercisesConstantsList.removeAll(previousExercises);
        return exercisesConstantsList;
    }
}
