package com.bkikenski.ultimatefitness.service.impl;

import com.bkikenski.ultimatefitness.model.Exercise;
import com.bkikenski.ultimatefitness.model.WeeklyResults;
import com.bkikenski.ultimatefitness.model.User;
import com.bkikenski.ultimatefitness.model.dto.ExerciseDTO;
import com.bkikenski.ultimatefitness.model.dto.RapidAPIResponseDTO;
import com.bkikenski.ultimatefitness.model.enumerations.ExercisesConstants;
import com.bkikenski.ultimatefitness.model.enumerations.Sex;
import com.bkikenski.ultimatefitness.repository.ExerciseRepository;
import com.bkikenski.ultimatefitness.repository.UserRepository;
import com.bkikenski.ultimatefitness.repository.WeeklyResultsRepository;
import com.bkikenski.ultimatefitness.service.ExerciseService;
import com.bkikenski.ultimatefitness.service.RapidAPIService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseServiceImplementation implements ExerciseService {
    private final UserRepository userRepository;
    private final WeeklyResultsRepository weeklyResultsRepository;
    private final ExerciseRepository exerciseRepository;
    private final RapidAPIService rapidAPIService;

    public ExerciseServiceImplementation(UserRepository userRepository, WeeklyResultsRepository weeklyResultsRepository,
                                         ExerciseRepository exerciseRepository, RapidAPIService rapidAPIService) {
        this.userRepository = userRepository;
        this.weeklyResultsRepository = weeklyResultsRepository;
        this.exerciseRepository = exerciseRepository;
        this.rapidAPIService = rapidAPIService;
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
        float userWeight = user.getCurrentWeight();
        WeeklyResults userLastResult = new WeeklyResults();
        List<Exercise> userLastResultExercises = new ArrayList<>();
        List<Integer> l = new ArrayList<>();
        l.add(0);

        if (user.getResults().size() == 1){
            List<Exercise> previousExercises = user.getResults().get(user.getResults().size() - 1).getExercisesResults();
            for (Exercise exercise : previousExercises) {
                Exercise e = Exercise.builder()
                        .exerciseName(exercise.getExerciseName())
                        .personalRecord(exercise.getPersonalRecord())
                        .previousWeights(new ArrayList<>())
                        .lastTrainingRepsPerSet(l)
                        .nextExceptedRepsPerSet(new ArrayList<>())
                        .currentWorkingWeight(exercise.getCurrentWorkingWeight())
                        .build();
                exerciseRepository.save(e);
                userLastResultExercises.add(e);
            }
        } else if (!user.getResults().isEmpty()) {
            List<Exercise> previousExercises = user.getResults().get(user.getResults().size() - 1).getExercisesResults();
            for (Exercise exercise : previousExercises) {
                Exercise e = Exercise.builder()
                        .exerciseName(exercise.getExerciseName())
                        .personalRecord(exercise.getPersonalRecord())
                        .previousWeights(exercise.getPreviousWeights())
                        .lastTrainingRepsPerSet(exercise.getLastTrainingRepsPerSet())
                        .nextExceptedRepsPerSet(new ArrayList<>())
                        .currentWorkingWeight(exercise.getCurrentWorkingWeight())
                        .build();
                exerciseRepository.save(e);
                userLastResultExercises.add(e);
            }
        }


        for (ExercisesConstants exercise: neverDoneExercises) {
            Exercise e = Exercise.builder()
                    .exerciseName(exercise)
                    .personalRecord(0)
                    .previousWeights(new ArrayList<>())
                    .lastTrainingRepsPerSet(l)
                    .nextExceptedRepsPerSet(new ArrayList<>())
                    .currentWorkingWeight(userSex == Sex.MALE ?
                            (float) (exercise.getRatioPerLevelMale().get(0) - 0.15) * userWeight :
                            (float) (exercise.getRatioPerLevelFemale().get(0) - 0.15) * userWeight)
                    .build();
            exerciseRepository.save(e);
            userLastResultExercises.add(e);

        }
        userLastResult.setExercisesResults(userLastResultExercises);
        userLastResult.setUser(user);
        weeklyResultsRepository.save(userLastResult);

        if (!user.getResults().isEmpty()){
            user.getResults().add(userLastResult);
        } else {
            List<WeeklyResults> tmp = new ArrayList<>();
            tmp.add(userLastResult);
            user.setResults(tmp);
        }
        userRepository.save(user);
    }

    @Override
    public List<ExercisesConstants> getExercisesForUser(User user) {
        List<WeeklyResults> userResults = user.getResults();

        if (userResults.isEmpty())
            return new ArrayList<>();
        else {
            List<Exercise> exercises = userResults.get(userResults.size() - 1).getExercisesResults();
            return exercises.stream()
                    .map(Exercise::getExerciseName)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<ExercisesConstants> getNeverDoneExercises(List<ExercisesConstants> previousExercises, List<ExercisesConstants> exercisesConstantsForLevel) {
        List<ExercisesConstants> exercisesConstantsList = new ArrayList<>(exercisesConstantsForLevel);
        exercisesConstantsList.removeAll(previousExercises);
        return exercisesConstantsList;
    }

    @Override
    public ExerciseDTO findExerciseById(Long id) throws IOException, InterruptedException {
        Exercise exercise = exerciseRepository.findById(id).orElseThrow();
        RapidAPIResponseDTO rapidAPIResponseDTO = rapidAPIService.call(exercise.getExerciseName().getRapidApiUrl());
        return ExerciseDTO.builder()
                .id(exercise.getId())
                .exerciseName(exercise.getExerciseName())
                .personalRecord(exercise.getPersonalRecord())
                .nextExceptedRepsPerSet(exercise.getNextExceptedRepsPerSet())
                .currentWorkingWeight(exercise.getCurrentWorkingWeight())
                .restApiInfo(rapidAPIResponseDTO)
                .build();
    }
}
