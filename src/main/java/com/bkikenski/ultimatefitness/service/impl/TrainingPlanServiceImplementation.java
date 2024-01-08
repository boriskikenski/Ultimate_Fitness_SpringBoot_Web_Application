package com.bkikenski.ultimatefitness.service.impl;

import com.bkikenski.ultimatefitness.model.Exercise;
import com.bkikenski.ultimatefitness.model.Training;
import com.bkikenski.ultimatefitness.model.User;
import com.bkikenski.ultimatefitness.model.enumerations.*;
import com.bkikenski.ultimatefitness.model.exceptions.UserNotFoundException;
import com.bkikenski.ultimatefitness.model.util.TrainingUtils;
import com.bkikenski.ultimatefitness.repository.UserRepository;
import com.bkikenski.ultimatefitness.service.ExerciseService;
import com.bkikenski.ultimatefitness.service.TrainingPlanService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TrainingPlanServiceImplementation implements TrainingPlanService {
    private final UserRepository userRepository;
    private final ExerciseService exerciseService;

    public TrainingPlanServiceImplementation(UserRepository userRepository, ExerciseService exerciseService) {
        this.userRepository = userRepository;
        this.exerciseService = exerciseService;
    }

    @Override
    public void generateTrainingPlan(Long userId) {
        FitnessPlans chosenPlan = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new)
                .getCurrentFitnessPlan();

        switch (chosenPlan) {
            case BODYBUILDING -> generateBodybuildingPlan(userId);
            case POWERLIFTING -> generatePowerliftingPlan(userId);
            case CROSSFIT -> generateCrossfitPlan(userId);
            case OLYMPIC -> generateOlympicPlan(userId);
            case CARDIO -> generateCardioPlan(userId);
        }
    }

    @Override
    public void generateBodybuildingPlan(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        FitnessLevels userFitnessLevel = user.getLevel();
        List<Training> trainingList = new ArrayList<>();
        List<Exercises> alreadyDoneExercises = exerciseService.getExercisesForUser(user);
        List<Exercises> neverDoneExercises;

        switch (userFitnessLevel) {
            case BEGINNER -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        TrainingUtils.BODYBUILDING_BEGINNER_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsBodybuilding(user);

                trainingList.add(getTraining(Days.MONDAY, TrainingUtils.PULL_DAY_MUSCLE_GROUPS, 60,
                        TrainingUtils.BODYBUILDING_BEGINNER_PULL_EXERCISES,
                        getRandomExercises(3, TrainingUtils.BODYBUILDING_ADDITIONAL_BEGINNER_PULL_EXERCISES)));

                trainingList.add(getTraining(Days.WEDNESDAY, TrainingUtils.PUSH_DAY_MUSCLE_GROUPS, 60,
                        TrainingUtils.BODYBUILDING_BEGINNER_PUSH_EXERCISES,
                        getRandomExercises(3, TrainingUtils.BODYBUILDING_ADDITIONAL_BEGINNER_PUSH_EXERCISES)));

                trainingList.add(getTraining(Days.FRIDAY, TrainingUtils.LEG_DAY_MUSCLE_GROUPS, 60,
                        TrainingUtils.BODYBUILDING_BEGINNER_LEG_EXERCISES,
                        getRandomExercises(3, TrainingUtils.BODYBUILDING_ADDITIONAL_BEGINNER_LEG_EXERCISES)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
            case INTERMEDIATE -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        TrainingUtils.BODYBUILDING_INTERMEDIATE_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsBodybuilding(user);

                trainingList.add(getTraining(Days.MONDAY, TrainingUtils.PULL_DAY_MUSCLE_GROUPS, 60,
                        TrainingUtils.BODYBUILDING_INTERMEDIATE_PULL_EXERCISES,
                        getRandomExercises(3, TrainingUtils.BODYBUILDING_ADDITIONAL_INTERMEDIATE_PULL_EXERCISES)));

                trainingList.add(getTraining(Days.TUESDAY, TrainingUtils.PUSH_DAY_MUSCLE_GROUPS, 60,
                        TrainingUtils.BODYBUILDING_INTERMEDIATE_PUSH_EXERCISES,
                        getRandomExercises(3, TrainingUtils.BODYBUILDING_ADDITIONAL_INTERMEDIATE_PUSH_EXERCISES)));

                trainingList.add(getTraining(Days.THURSDAY, TrainingUtils.LEG_DAY_MUSCLE_GROUPS, 60,
                        TrainingUtils.BODYBUILDING_INTERMEDIATE_LEG_EXERCISES,
                        getRandomExercises(3, TrainingUtils.BODYBUILDING_ADDITIONAL_INTERMEDIATE_LEG_EXERCISES)));

                trainingList.add(getTraining(Days.SATURDAY, TrainingUtils.LEG_DAY_MUSCLE_GROUPS, 60,
                        TrainingUtils.BODYBUILDING_INTERMEDIATE_FULL_EXERCISES,
                        getRandomExercises(3, TrainingUtils.BODYBUILDING_ADDITIONAL_INTERMEDIATE_FULL_EXERCISES)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
            case ADVANCED -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        TrainingUtils.BODYBUILDING_ADVANCED_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsBodybuilding(user);

                trainingList.add(getTraining(Days.MONDAY, TrainingUtils.PULL_DAY_MUSCLE_GROUPS, 50,
                        TrainingUtils.BODYBUILDING_ADVANCED_PULL_EXERCISES,
                        getRandomExercises(3, TrainingUtils.BODYBUILDING_ADDITIONAL_ADVANCED_PULL_EXERCISES)));

                trainingList.add(getTraining(Days.TUESDAY, TrainingUtils.PUSH_DAY_MUSCLE_GROUPS, 50,
                        TrainingUtils.BODYBUILDING_ADVANCED_PUSH_EXERCISES,
                        getRandomExercises(3, TrainingUtils.BODYBUILDING_ADDITIONAL_ADVANCED_PUSH_EXERCISES)));

                trainingList.add(getTraining(Days.WEDNESDAY, TrainingUtils.LEG_DAY_MUSCLE_GROUPS, 50,
                        TrainingUtils.BODYBUILDING_ADVANCED_LEG_EXERCISES,
                        getRandomExercises(3, TrainingUtils.BODYBUILDING_ADDITIONAL_ADVANCED_LEG_EXERCISES)));

                trainingList.add(getTraining(Days.FRIDAY, TrainingUtils.PULL_DAY_MUSCLE_GROUPS, 50,
                        TrainingUtils.BODYBUILDING_ADVANCED_PULL_EXERCISES,
                        getRandomExercises(3, TrainingUtils.BODYBUILDING_ADDITIONAL_ADVANCED_PULL_EXERCISES)));

                trainingList.add(getTraining(Days.SATURDAY, TrainingUtils.PUSH_DAY_MUSCLE_GROUPS, 50,
                        TrainingUtils.BODYBUILDING_ADVANCED_PUSH_EXERCISES,
                        getRandomExercises(3, TrainingUtils.BODYBUILDING_ADDITIONAL_ADVANCED_PUSH_EXERCISES)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
            case EXPERT -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        TrainingUtils.BODYBUILDING_EXPERT_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsBodybuilding(user);

                trainingList.add(getTraining(Days.MONDAY, TrainingUtils.PULL_DAY_MUSCLE_GROUPS, 50,
                        TrainingUtils.BODYBUILDING_EXPERT_PULL_EXERCISES,
                        getRandomExercises(3, TrainingUtils.BODYBUILDING_ADDITIONAL_EXPERT_PULL_EXERCISES)));

                trainingList.add(getTraining(Days.TUESDAY, TrainingUtils.PUSH_DAY_MUSCLE_GROUPS, 50,
                        TrainingUtils.BODYBUILDING_EXPERT_PUSH_EXERCISES,
                        getRandomExercises(3, TrainingUtils.BODYBUILDING_ADDITIONAL_EXPERT_PUSH_EXERCISES)));

                trainingList.add(getTraining(Days.WEDNESDAY, TrainingUtils.LEG_DAY_MUSCLE_GROUPS, 50,
                        TrainingUtils.BODYBUILDING_EXPERT_LEG_EXERCISES,
                        getRandomExercises(3, TrainingUtils.BODYBUILDING_ADDITIONAL_EXPERT_LEG_EXERCISES)));

                trainingList.add(getTraining(Days.THURSDAY, TrainingUtils.PULL_DAY_MUSCLE_GROUPS, 50,
                        TrainingUtils.BODYBUILDING_EXPERT_PULL_EXERCISES,
                        getRandomExercises(3, TrainingUtils.BODYBUILDING_ADDITIONAL_EXPERT_PULL_EXERCISES)));

                trainingList.add(getTraining(Days.FRIDAY, TrainingUtils.PUSH_DAY_MUSCLE_GROUPS, 50,
                        TrainingUtils.BODYBUILDING_EXPERT_PUSH_EXERCISES,
                        getRandomExercises(3, TrainingUtils.BODYBUILDING_ADDITIONAL_EXPERT_PUSH_EXERCISES)));

                trainingList.add(getTraining(Days.SATURDAY, TrainingUtils.LEG_DAY_MUSCLE_GROUPS, 50,
                        TrainingUtils.BODYBUILDING_EXPERT_LEG_EXERCISES,
                        getRandomExercises(3, TrainingUtils.BODYBUILDING_ADDITIONAL_EXPERT_LEG_EXERCISES)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
            case PROFESSIONAL -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        TrainingUtils.BODYBUILDING_PROFESSIONAL_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsBodybuilding(user);

                trainingList.add(getTraining(Days.MONDAY, TrainingUtils.PULL_DAY_MUSCLE_GROUPS, 50,
                        TrainingUtils.BODYBUILDING_PROFESSIONAL_PULL_EXERCISES,
                        getRandomExercises(3, TrainingUtils.BODYBUILDING_ADDITIONAL_PROFESSIONAL_PULL_EXERCISES)));

                trainingList.add(getTraining(Days.TUESDAY, TrainingUtils.PUSH_DAY_MUSCLE_GROUPS, 50,
                        TrainingUtils.BODYBUILDING_PROFESSIONAL_PUSH_EXERCISES,
                        getRandomExercises(3, TrainingUtils.BODYBUILDING_ADDITIONAL_PROFESSIONAL_PUSH_EXERCISES)));

                trainingList.add(getTraining(Days.WEDNESDAY, TrainingUtils.LEG_DAY_MUSCLE_GROUPS, 50,
                        TrainingUtils.BODYBUILDING_PROFESSIONAL_LEG_EXERCISES,
                        getRandomExercises(3, TrainingUtils.BODYBUILDING_ADDITIONAL_PROFESSIONAL_LEG_EXERCISES)));

                trainingList.add(getTraining(Days.THURSDAY, TrainingUtils.PULL_DAY_MUSCLE_GROUPS, 50,
                        TrainingUtils.BODYBUILDING_PROFESSIONAL_PULL_EXERCISES,
                        getRandomExercises(3, TrainingUtils.BODYBUILDING_ADDITIONAL_PROFESSIONAL_PULL_EXERCISES)));

                trainingList.add(getTraining(Days.FRIDAY, TrainingUtils.PUSH_DAY_MUSCLE_GROUPS, 50,
                        TrainingUtils.BODYBUILDING_PROFESSIONAL_PUSH_EXERCISES,
                        getRandomExercises(3, TrainingUtils.BODYBUILDING_ADDITIONAL_PROFESSIONAL_PUSH_EXERCISES)));

                trainingList.add(getTraining(Days.SATURDAY, TrainingUtils.LEG_DAY_MUSCLE_GROUPS, 50,
                        TrainingUtils.BODYBUILDING_PROFESSIONAL_LEG_EXERCISES,
                        getRandomExercises(3, TrainingUtils.BODYBUILDING_ADDITIONAL_PROFESSIONAL_LEG_EXERCISES)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
        }
    }

    @Override
    public void setWeightAndRepsBodybuilding(User user) {
        List<Exercise> exercises = user.getResults().get(user.getResults().size() - 1).getExercisesResults();

        for (Exercise exercise: exercises) {
            int lastWorkingReps = exercise.getLastTrainingRepsPerSet().stream().mapToInt(Integer::intValue).sum();

            if (lastWorkingReps < 32)
                exercise.setNextExceptedRepsPerSet(List.of(8, 8, 8, 8));
            else if (lastWorkingReps < 34)
                exercise.setNextExceptedRepsPerSet(List.of(9, 9, 8, 8));
            else if (lastWorkingReps < 36)
                exercise.setNextExceptedRepsPerSet(List.of(9, 9, 9, 9));
            else if (lastWorkingReps < 38)
                exercise.setNextExceptedRepsPerSet(List.of(10, 10, 9, 9));
            else if (lastWorkingReps < 40)
                exercise.setNextExceptedRepsPerSet(List.of(10, 10, 10, 10));
            else if (lastWorkingReps < 42)
                exercise.setNextExceptedRepsPerSet(List.of(11, 11, 10, 10));
            else if (lastWorkingReps < 44)
                exercise.setNextExceptedRepsPerSet(List.of(11, 11, 11, 11));
            else if (lastWorkingReps < 46)
                exercise.setNextExceptedRepsPerSet(List.of(12, 12, 11, 11));
            else if (lastWorkingReps < 48)
                exercise.setNextExceptedRepsPerSet(List.of(12, 12, 12, 12));
            else {
                float currentWeight = exercise.getCurrentWorkingWeight();
                exercise.getPreviousWeights().add(currentWeight);
                exercise.setCurrentWorkingWeight(currentWeight + 2.5f);
                exercise.setNextExceptedRepsPerSet(List.of(8, 8, 8, 8));
            }
        }

        userRepository.save(user);
    }

    @Override
    public void generatePowerliftingPlan(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        FitnessLevels userFitnessLevel = user.getLevel();
        List<Exercises> alreadyDoneExercises = exerciseService.getExercisesForUser(user);
        List<Exercises> neverDoneExercises;
        List<Training> trainingList = new ArrayList<>();

        switch (userFitnessLevel) {
            case BEGINNER -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        TrainingUtils.POWERLIFTING_BEGINNER_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsPowerlifting(user);

                trainingList.add(getTraining(Days.MONDAY, TrainingUtils.PULL_DAY_MUSCLE_GROUPS, 110,
                        TrainingUtils.POWERLIFTING_BEGINNER_PULL_EXERCISES,
                        getRandomExercises(3, TrainingUtils.POWERLIFTING_ADDITIONAL_BEGINNER_PULL_EXERCISES)));

                trainingList.add(getTraining(Days.WEDNESDAY, TrainingUtils.PUSH_DAY_MUSCLE_GROUPS, 110,
                        TrainingUtils.POWERLIFTING_BEGINNER_PUSH_EXERCISES,
                        getRandomExercises(3, TrainingUtils.POWERLIFTING_ADDITIONAL_BEGINNER_PUSH_EXERCISES)));

                trainingList.add(getTraining(Days.FRIDAY, TrainingUtils.LEG_DAY_MUSCLE_GROUPS, 110,
                        TrainingUtils.POWERLIFTING_BEGINNER_LEG_EXERCISES,
                        getRandomExercises(3, TrainingUtils.POWERLIFTING_ADDITIONAL_BEGINNER_LEG_EXERCISES)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
            case INTERMEDIATE -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        TrainingUtils.POWERLIFTING_INTERMEDIATE_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsPowerlifting(user);

                trainingList.add(getTraining(Days.MONDAY, TrainingUtils.PULL_DAY_MUSCLE_GROUPS, 130,
                        TrainingUtils.POWERLIFTING_INTERMEDIATE_PULL_EXERCISES,
                        getRandomExercises(3, TrainingUtils.POWERLIFTING_ADDITIONAL_INTERMEDIATE_PULL_EXERCISES)));

                trainingList.add(getTraining(Days.WEDNESDAY, TrainingUtils.PUSH_DAY_MUSCLE_GROUPS, 130,
                        TrainingUtils.POWERLIFTING_INTERMEDIATE_PUSH_EXERCISES,
                        getRandomExercises(3, TrainingUtils.POWERLIFTING_ADDITIONAL_INTERMEDIATE_PUSH_EXERCISES)));

                trainingList.add(getTraining(Days.FRIDAY, TrainingUtils.LEG_DAY_MUSCLE_GROUPS, 130,
                        TrainingUtils.POWERLIFTING_INTERMEDIATE_LEG_EXERCISES,
                        getRandomExercises(3, TrainingUtils.POWERLIFTING_ADDITIONAL_INTERMEDIATE_LEG_EXERCISES)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
            case ADVANCED -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        TrainingUtils.POWERLIFTING_ADVANCED_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsPowerlifting(user);

                trainingList.add(getTraining(Days.MONDAY, TrainingUtils.PULL_DAY_MUSCLE_GROUPS, 130,
                        TrainingUtils.POWERLIFTING_ADVANCED_PULL_EXERCISES,
                        getRandomExercises(3, TrainingUtils.POWERLIFTING_ADDITIONAL_ADVANCED_PULL_EXERCISES)));

                trainingList.add(getTraining(Days.TUESDAY, TrainingUtils.PUSH_DAY_MUSCLE_GROUPS, 130,
                        TrainingUtils.POWERLIFTING_ADVANCED_PUSH_EXERCISES,
                        getRandomExercises(3, TrainingUtils.POWERLIFTING_ADDITIONAL_ADVANCED_PUSH_EXERCISES)));

                trainingList.add(getTraining(Days.THURSDAY, TrainingUtils.LEG_DAY_MUSCLE_GROUPS, 130,
                        TrainingUtils.POWERLIFTING_ADVANCED_LEG_EXERCISES,
                        getRandomExercises(3, TrainingUtils.POWERLIFTING_ADDITIONAL_ADVANCED_LEG_EXERCISES)));

                trainingList.add(getTraining(Days.SATURDAY, TrainingUtils.LEG_DAY_MUSCLE_GROUPS, 60,
                        TrainingUtils.POWERLIFTING_ADVANCED_FULL_EXERCISES,
                        getRandomExercises(2, TrainingUtils.POWERLIFTING_ADDITIONAL_ADVANCED_FULL_EXERCISES)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
            case EXPERT -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        TrainingUtils.POWERLIFTING_EXPERT_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsPowerlifting(user);

                trainingList.add(getTraining(Days.MONDAY, TrainingUtils.PULL_DAY_MUSCLE_GROUPS, 150,
                        TrainingUtils.POWERLIFTING_EXPERT_PULL_EXERCISES,
                        getRandomExercises(3, TrainingUtils.POWERLIFTING_ADDITIONAL_EXPERT_PULL_EXERCISES)));

                trainingList.add(getTraining(Days.TUESDAY, TrainingUtils.PUSH_DAY_MUSCLE_GROUPS, 150,
                        TrainingUtils.POWERLIFTING_EXPERT_PUSH_EXERCISES,
                        getRandomExercises(3, TrainingUtils.POWERLIFTING_ADDITIONAL_EXPERT_PUSH_EXERCISES)));

                trainingList.add(getTraining(Days.WEDNESDAY, TrainingUtils.LEG_DAY_MUSCLE_GROUPS, 150,
                        TrainingUtils.POWERLIFTING_EXPERT_LEG_EXERCISES,
                        getRandomExercises(3, TrainingUtils.POWERLIFTING_ADDITIONAL_EXPERT_LEG_EXERCISES)));

                trainingList.add(getTraining(Days.FRIDAY, TrainingUtils.PULL_DAY_MUSCLE_GROUPS, 150,
                        TrainingUtils.POWERLIFTING_EXPERT_PULL_EXERCISES,
                        getRandomExercises(3, TrainingUtils.POWERLIFTING_ADDITIONAL_EXPERT_PULL_EXERCISES)));

                trainingList.add(getTraining(Days.SATURDAY, TrainingUtils.PUSH_DAY_MUSCLE_GROUPS, 150,
                        TrainingUtils.POWERLIFTING_EXPERT_PUSH_EXERCISES,
                        getRandomExercises(3, TrainingUtils.POWERLIFTING_ADDITIONAL_EXPERT_PUSH_EXERCISES)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
            case PROFESSIONAL -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        TrainingUtils.POWERLIFTING_PROFESSIONAL_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);
                setWeightAndRepsPowerlifting(user);

                trainingList.add(getTraining(Days.MONDAY, TrainingUtils.PULL_DAY_MUSCLE_GROUPS, 150,
                        TrainingUtils.POWERLIFTING_PROFESSIONAL_PULL_EXERCISES,
                        getRandomExercises(3, TrainingUtils.POWERLIFTING_ADDITIONAL_PROFESSIONAL_PULL_EXERCISES)));

                trainingList.add(getTraining(Days.TUESDAY, TrainingUtils.PUSH_DAY_MUSCLE_GROUPS, 150,
                        TrainingUtils.POWERLIFTING_PROFESSIONAL_PUSH_EXERCISES,
                        getRandomExercises(3, TrainingUtils.POWERLIFTING_ADDITIONAL_PROFESSIONAL_PUSH_EXERCISES)));

                trainingList.add(getTraining(Days.WEDNESDAY, TrainingUtils.LEG_DAY_MUSCLE_GROUPS, 150,
                        TrainingUtils.POWERLIFTING_PROFESSIONAL_LEG_EXERCISES,
                        getRandomExercises(3, TrainingUtils.POWERLIFTING_ADDITIONAL_PROFESSIONAL_LEG_EXERCISES)));

                trainingList.add(getTraining(Days.THURSDAY, TrainingUtils.PULL_DAY_MUSCLE_GROUPS, 150,
                        TrainingUtils.POWERLIFTING_PROFESSIONAL_PULL_EXERCISES,
                        getRandomExercises(3, TrainingUtils.POWERLIFTING_ADDITIONAL_PROFESSIONAL_PULL_EXERCISES)));

                trainingList.add(getTraining(Days.FRIDAY, TrainingUtils.PUSH_DAY_MUSCLE_GROUPS, 150,
                        TrainingUtils.POWERLIFTING_PROFESSIONAL_PUSH_EXERCISES,
                        getRandomExercises(3, TrainingUtils.POWERLIFTING_ADDITIONAL_PROFESSIONAL_PUSH_EXERCISES)));

                trainingList.add(getTraining(Days.SATURDAY, TrainingUtils.LEG_DAY_MUSCLE_GROUPS, 150,
                        TrainingUtils.POWERLIFTING_PROFESSIONAL_LEG_EXERCISES,
                        getRandomExercises(3, TrainingUtils.POWERLIFTING_ADDITIONAL_PROFESSIONAL_LEG_EXERCISES)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
        }
    }

    @Override
    public void setWeightAndRepsPowerlifting(User user) {
        List<Exercise> exercises = user.getResults().get(user.getResults().size() - 1).getExercisesResults();

        for (Exercise exercise : exercises) {
            int lastWorkingReps = exercise.getLastTrainingRepsPerSet().stream().mapToInt(Integer::intValue).sum();

            if (lastWorkingReps < 15)
                exercise.setNextExceptedRepsPerSet(List.of(3, 3, 3, 3, 3));
            else if (lastWorkingReps < 18)
                exercise.setNextExceptedRepsPerSet(List.of(4, 4, 4, 3, 3));
            else if (lastWorkingReps < 20)
                exercise.setNextExceptedRepsPerSet(List.of(4, 4, 4, 4, 4));
            else if (lastWorkingReps < 23)
                exercise.setNextExceptedRepsPerSet(List.of(5, 5, 5, 4, 4));
            else {
                float currentWeight = exercise.getCurrentWorkingWeight();
                exercise.getPreviousWeights().add(currentWeight);
                exercise.setCurrentWorkingWeight(currentWeight + 2.5f);
                exercise.setNextExceptedRepsPerSet(List.of(3, 3, 3, 3, 3));
            }
        }

        userRepository.save(user);
    }

    @Override
    public void generateCrossfitPlan(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        List<Training> trainingList = new ArrayList<>();
        FitnessLevels userFitnessLevel = user.getLevel();
        List<Exercises> alreadyDoneExercises = exerciseService.getExercisesForUser(user);
        List<Exercises> neverDoneExercises;

        switch (userFitnessLevel) {
            case BEGINNER -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        TrainingUtils.CROSSFIT_BEGINNER_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsCrossfit(user);

                trainingList.add(getTraining(Days.MONDAY, TrainingUtils.FULL_BODY, 10,
                        TrainingUtils.CROSSFIT_BEGINNER_TRAINING_ONE,
                        getRandomExercises(3, TrainingUtils.CROSSFIT_ADDITIONAL_BEGINNER_TRAINING_ONE)));

                trainingList.add(getTraining(Days.WEDNESDAY, TrainingUtils.FULL_BODY, 10,
                        TrainingUtils.CROSSFIT_BEGINNER_TRAINING_TWO,
                        getRandomExercises(3, TrainingUtils.CROSSFIT_ADDITIONAL_BEGINNER_TRAINING_TWO)));

                trainingList.add(getTraining(Days.FRIDAY, TrainingUtils.FULL_BODY, 10,
                        TrainingUtils.CROSSFIT_BEGINNER_TRAINING_THREE,
                        getRandomExercises(3, TrainingUtils.CROSSFIT_ADDITIONAL_BEGINNER_TRAINING_THREE)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
            case INTERMEDIATE -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        TrainingUtils.CROSSFIT_INTERMEDIATE_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsCrossfit(user);

                trainingList.add(getTraining(Days.MONDAY, TrainingUtils.FULL_BODY, 10,
                        TrainingUtils.CROSSFIT_INTERMEDIATE_TRAINING_ONE,
                        getRandomExercises(3, TrainingUtils.CROSSFIT_ADDITIONAL_INTERMEDIATE_TRAINING_ONE)));

                trainingList.add(getTraining(Days.WEDNESDAY, TrainingUtils.FULL_BODY, 10,
                        TrainingUtils.CROSSFIT_INTERMEDIATE_TRAINING_TWO,
                        getRandomExercises(3, TrainingUtils.CROSSFIT_ADDITIONAL_INTERMEDIATE_TRAINING_TWO)));

                trainingList.add(getTraining(Days.FRIDAY, TrainingUtils.FULL_BODY, 10,
                        TrainingUtils.CROSSFIT_INTERMEDIATE_TRAINING_THREE,
                        getRandomExercises(3, TrainingUtils.CROSSFIT_ADDITIONAL_INTERMEDIATE_TRAINING_THREE)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
            case ADVANCED -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        TrainingUtils.CROSSFIT_ADVANCED_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsCrossfit(user);

                trainingList.add(getTraining(Days.MONDAY, TrainingUtils.FULL_BODY, 10,
                        TrainingUtils.CROSSFIT_ADVANCED_TRAINING_ONE,
                        getRandomExercises(3, TrainingUtils.CROSSFIT_ADDITIONAL_ADVANCED_TRAINING_ONE)));

                trainingList.add(getTraining(Days.WEDNESDAY, TrainingUtils.FULL_BODY, 10,
                        TrainingUtils.CROSSFIT_ADVANCED_TRAINING_TWO,
                        getRandomExercises(3, TrainingUtils.CROSSFIT_ADDITIONAL_ADVANCED_TRAINING_TWO)));

                trainingList.add(getTraining(Days.FRIDAY, TrainingUtils.FULL_BODY, 10,
                        TrainingUtils.CROSSFIT_ADVANCED_TRAINING_THREE,
                        getRandomExercises(3, TrainingUtils.CROSSFIT_ADDITIONAL_ADVANCED_TRAINING_THREE)));

                trainingList.add(getTraining(Days.FRIDAY, TrainingUtils.FULL_BODY, 10,
                        TrainingUtils.CROSSFIT_ADVANCED_TRAINING_FOUR,
                        getRandomExercises(3, TrainingUtils.CROSSFIT_ADDITIONAL_ADVANCED_TRAINING_FOUR)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
            case EXPERT -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        TrainingUtils.CROSSFIT_EXPERT_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsCrossfit(user);

                trainingList.add(getTraining(Days.MONDAY, TrainingUtils.FULL_BODY, 10,
                        TrainingUtils.CROSSFIT_EXPERT_TRAINING_ONE,
                        getRandomExercises(3, TrainingUtils.CROSSFIT_ADDITIONAL_EXPERT_TRAINING_ONE)));

                trainingList.add(getTraining(Days.WEDNESDAY, TrainingUtils.FULL_BODY, 10,
                        TrainingUtils.CROSSFIT_EXPERT_TRAINING_TWO,
                        getRandomExercises(3, TrainingUtils.CROSSFIT_ADDITIONAL_EXPERT_TRAINING_TWO)));

                trainingList.add(getTraining(Days.FRIDAY, TrainingUtils.FULL_BODY, 10,
                        TrainingUtils.CROSSFIT_EXPERT_TRAINING_THREE,
                        getRandomExercises(3, TrainingUtils.CROSSFIT_ADDITIONAL_EXPERT_TRAINING_THREE)));

                trainingList.add(getTraining(Days.FRIDAY, TrainingUtils.FULL_BODY, 10,
                        TrainingUtils.CROSSFIT_EXPERT_TRAINING_FOUR,
                        getRandomExercises(3, TrainingUtils.CROSSFIT_ADDITIONAL_EXPERT_TRAINING_FOUR)));

                trainingList.add(getTraining(Days.FRIDAY, TrainingUtils.FULL_BODY, 10,
                        TrainingUtils.CROSSFIT_EXPERT_TRAINING_FIVE,
                        getRandomExercises(3, TrainingUtils.CROSSFIT_ADDITIONAL_EXPERT_TRAINING_FIVE)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
            case PROFESSIONAL -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        TrainingUtils.CROSSFIT_PROFESSIONAL_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsCrossfit(user);

                trainingList.add(getTraining(Days.MONDAY, TrainingUtils.FULL_BODY, 10,
                        TrainingUtils.CROSSFIT_PROFESSIONAL_TRAINING_ONE,
                        getRandomExercises(3, TrainingUtils.CROSSFIT_ADDITIONAL_PROFESSIONAL_TRAINING_ONE)));

                trainingList.add(getTraining(Days.WEDNESDAY, TrainingUtils.FULL_BODY, 10,
                        TrainingUtils.CROSSFIT_PROFESSIONAL_TRAINING_TWO,
                        getRandomExercises(3, TrainingUtils.CROSSFIT_ADDITIONAL_PROFESSIONAL_TRAINING_TWO)));

                trainingList.add(getTraining(Days.FRIDAY, TrainingUtils.FULL_BODY, 10,
                        TrainingUtils.CROSSFIT_PROFESSIONAL_TRAINING_THREE,
                        getRandomExercises(3, TrainingUtils.CROSSFIT_ADDITIONAL_PROFESSIONAL_TRAINING_THREE)));

                trainingList.add(getTraining(Days.FRIDAY, TrainingUtils.FULL_BODY, 10,
                        TrainingUtils.CROSSFIT_PROFESSIONAL_TRAINING_FOUR,
                        getRandomExercises(3, TrainingUtils.CROSSFIT_ADDITIONAL_PROFESSIONAL_TRAINING_FOUR)));

                trainingList.add(getTraining(Days.FRIDAY, TrainingUtils.FULL_BODY, 10,
                        TrainingUtils.CROSSFIT_PROFESSIONAL_TRAINING_FIVE,
                        getRandomExercises(3, TrainingUtils.CROSSFIT_ADDITIONAL_PROFESSIONAL_TRAINING_FIVE)));

                trainingList.add(getTraining(Days.FRIDAY, TrainingUtils.FULL_BODY, 10,
                        TrainingUtils.CROSSFIT_PROFESSIONAL_TRAINING_SIX,
                        getRandomExercises(3, TrainingUtils.CROSSFIT_ADDITIONAL_PROFESSIONAL_TRAINING_SIX)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
        }
    }

    @Override
    public void setWeightAndRepsCrossfit(User user) {
        List<Exercise> exercises = user.getResults().get(user.getResults().size() - 1).getExercisesResults();

        for (Exercise exercise : exercises) {
            int lastWorkingReps = exercise.getLastTrainingRepsPerSet().stream().mapToInt(Integer::intValue).sum();

            if (lastWorkingReps < 75)
                exercise.setNextExceptedRepsPerSet(List.of(15, 15, 15, 15, 15));
            else if (lastWorkingReps < 90)
                exercise.setNextExceptedRepsPerSet(List.of(15, 15, 15, 15, 15, 15));
            else if (lastWorkingReps < 105)
                exercise.setNextExceptedRepsPerSet(List.of(15, 15, 15, 15, 15, 15, 15));
            else if (lastWorkingReps < 120)
                exercise.setNextExceptedRepsPerSet(List.of(15, 15, 15, 15, 15, 15, 15, 15));
            else if (lastWorkingReps < 135)
                exercise.setNextExceptedRepsPerSet(List.of(15, 15, 15, 15, 15, 15, 15, 15, 15));
            else if (lastWorkingReps < 150)
                exercise.setNextExceptedRepsPerSet(List.of(15, 15, 15, 15, 15, 15, 15, 15, 15, 15));
            else {
                float currentWeight = exercise.getCurrentWorkingWeight();
                exercise.getPreviousWeights().add(currentWeight);
                exercise.setCurrentWorkingWeight(currentWeight + 2.5f);
                exercise.setNextExceptedRepsPerSet(List.of(15, 15, 15, 15, 15));
            }
        }

        userRepository.save(user);
    }

    @Override
    public void generateOlympicPlan(Long userId) {

    }

    @Override
    public void generateCardioPlan(Long userId) {

    }

    @Override
    public Training getTraining(Days day, List<MuscleGroup> muscleGroups, int restTimeSeconds, List<Exercises> baseExercises,
                                List<Exercises> additionalExercises) {
        return Training.builder()
                .day(day)
                .forMuscleGroup(muscleGroups)
                .restTimeSeconds(restTimeSeconds)
                .exercises(Collections.unmodifiableList(
                        Stream.concat(baseExercises.stream(), additionalExercises.stream())
                        .collect(Collectors.toList())))
                .build();
    }

    @Override
    public List<Exercises> getRandomExercises(int numberOfExercises, List<Exercises> exercisesFromList) {
        return null; //TODO
    }
}
