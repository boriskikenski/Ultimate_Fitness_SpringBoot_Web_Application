package com.bkikenski.ultimatefitness.service.impl;

import com.bkikenski.ultimatefitness.model.entity.Exercise;
import com.bkikenski.ultimatefitness.model.entity.Training;
import com.bkikenski.ultimatefitness.model.entity.User;
import com.bkikenski.ultimatefitness.model.dto.ExerciseTrainingPageDTO;
import com.bkikenski.ultimatefitness.model.dto.TrainingDTO;
import com.bkikenski.ultimatefitness.model.enumerations.Days;
import com.bkikenski.ultimatefitness.model.enumerations.ExercisesConstants;
import com.bkikenski.ultimatefitness.model.exceptions.RestDayException;
import com.bkikenski.ultimatefitness.model.exceptions.TrainingNotFoundException;
import com.bkikenski.ultimatefitness.model.exceptions.UserNotFoundException;
import com.bkikenski.ultimatefitness.repository.TrainingRepository;
import com.bkikenski.ultimatefitness.repository.UserRepository;
import com.bkikenski.ultimatefitness.service.TrainingService;
import com.bkikenski.ultimatefitness.service.utility.UtilityService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TrainingServiceImplementation implements TrainingService {
    private final UserRepository userRepository;
    private final TrainingRepository trainingRepository;

    public TrainingServiceImplementation(UserRepository userRepository, TrainingRepository trainingRepository) {
        this.userRepository = userRepository;
        this.trainingRepository = trainingRepository;
    }

    @Override
    public TrainingDTO findById(Long trainingId) {
        Training training = trainingRepository.findById(trainingId).orElseThrow(TrainingNotFoundException::new);
        return castToTrainingDTO(training);
    }

    @Override
    public TrainingDTO getTodayTraining(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        Days today = UtilityService.getDay(LocalDate.now());
        List<Training> userTrainingPlan = user.getTrainingPlan();
        Training training = userTrainingPlan.stream()
                .filter(t -> t.getDay().equals(today))
                .findFirst()
                .orElseThrow(RestDayException::new);

        return castToTrainingDTO(training);
    }

    private TrainingDTO castToTrainingDTO(Training training) {
        List<ExerciseTrainingPageDTO> exercises = getExercisesTrainingPageInfo(training);

        return TrainingDTO.builder()
                .forMuscleGroup(training.getForMuscleGroup())
                .restTimeSeconds(training.getRestTimeSeconds())
                .exercises(exercises)
                .build();
    }

    private List<ExerciseTrainingPageDTO> getExercisesTrainingPageInfo(Training training){
        User user = training.getUser();
        List<Exercise> exercises = user.getResults().get(user.getResults().size() - 1).getExercisesResults();
        List<ExercisesConstants> todayExercisesNames = training.getExercises();
        return exercises.stream()
                .filter(exercise -> todayExercisesNames.contains(exercise.getExerciseName()))
                .map(exercise -> ExerciseTrainingPageDTO.builder()
                        .exerciseName(exercise.getExerciseName())
                        .id(exercise.getId())
                        .build())
                .toList();
    }

}
