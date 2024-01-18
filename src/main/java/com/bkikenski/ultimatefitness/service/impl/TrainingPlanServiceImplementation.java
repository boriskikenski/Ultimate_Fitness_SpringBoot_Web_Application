package com.bkikenski.ultimatefitness.service.impl;

import com.bkikenski.ultimatefitness.model.Exercise;
import com.bkikenski.ultimatefitness.model.Training;
import com.bkikenski.ultimatefitness.model.User;
import com.bkikenski.ultimatefitness.model.enumerations.*;
import com.bkikenski.ultimatefitness.model.exceptions.UserNotFoundException;
import com.bkikenski.ultimatefitness.repository.UserRepository;
import com.bkikenski.ultimatefitness.service.ExerciseService;
import com.bkikenski.ultimatefitness.service.TrainingPlanService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.bkikenski.ultimatefitness.model.util.TrainingUtils.*;

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
                        BODYBUILDING_BEGINNER_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsBodybuilding(user);

                trainingList.add(getTraining(Days.MONDAY, PULL_DAY_MUSCLE_GROUPS, 60,
                        BODYBUILDING_BEGINNER_PULL_EXERCISES,
                        getRandomExercises(3, BODYBUILDING_ADDITIONAL_BEGINNER_PULL_EXERCISES)));

                trainingList.add(getTraining(Days.WEDNESDAY, PUSH_DAY_MUSCLE_GROUPS, 60,
                        BODYBUILDING_BEGINNER_PUSH_EXERCISES,
                        getRandomExercises(3, BODYBUILDING_ADDITIONAL_BEGINNER_PUSH_EXERCISES)));

                trainingList.add(getTraining(Days.FRIDAY, LEG_DAY_MUSCLE_GROUPS, 60,
                        BODYBUILDING_BEGINNER_LEG_EXERCISES,
                        getRandomExercises(3, BODYBUILDING_ADDITIONAL_BEGINNER_LEG_EXERCISES)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
            case INTERMEDIATE -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        BODYBUILDING_INTERMEDIATE_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsBodybuilding(user);

                trainingList.add(getTraining(Days.MONDAY, PULL_DAY_MUSCLE_GROUPS, 60,
                        BODYBUILDING_INTERMEDIATE_PULL_EXERCISES,
                        getRandomExercises(3, BODYBUILDING_ADDITIONAL_INTERMEDIATE_PULL_EXERCISES)));

                trainingList.add(getTraining(Days.TUESDAY, PUSH_DAY_MUSCLE_GROUPS, 60,
                        BODYBUILDING_INTERMEDIATE_PUSH_EXERCISES,
                        getRandomExercises(3, BODYBUILDING_ADDITIONAL_INTERMEDIATE_PUSH_EXERCISES)));

                trainingList.add(getTraining(Days.THURSDAY, LEG_DAY_MUSCLE_GROUPS, 60,
                        BODYBUILDING_INTERMEDIATE_LEG_EXERCISES,
                        getRandomExercises(3, BODYBUILDING_ADDITIONAL_INTERMEDIATE_LEG_EXERCISES)));

                trainingList.add(getTraining(Days.SATURDAY, LEG_DAY_MUSCLE_GROUPS, 60,
                        BODYBUILDING_INTERMEDIATE_FULL_EXERCISES,
                        getRandomExercises(3, BODYBUILDING_ADDITIONAL_INTERMEDIATE_FULL_EXERCISES)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
            case ADVANCED -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        BODYBUILDING_ADVANCED_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsBodybuilding(user);

                trainingList.add(getTraining(Days.MONDAY, PULL_DAY_MUSCLE_GROUPS, 50,
                        BODYBUILDING_ADVANCED_PULL_EXERCISES,
                        getRandomExercises(3, BODYBUILDING_ADDITIONAL_ADVANCED_PULL_EXERCISES)));

                trainingList.add(getTraining(Days.TUESDAY, PUSH_DAY_MUSCLE_GROUPS, 50,
                        BODYBUILDING_ADVANCED_PUSH_EXERCISES,
                        getRandomExercises(3, BODYBUILDING_ADDITIONAL_ADVANCED_PUSH_EXERCISES)));

                trainingList.add(getTraining(Days.WEDNESDAY, LEG_DAY_MUSCLE_GROUPS, 50,
                        BODYBUILDING_ADVANCED_LEG_EXERCISES,
                        getRandomExercises(3, BODYBUILDING_ADDITIONAL_ADVANCED_LEG_EXERCISES)));

                trainingList.add(getTraining(Days.FRIDAY, PULL_DAY_MUSCLE_GROUPS, 50,
                        BODYBUILDING_ADVANCED_PULL_EXERCISES,
                        getRandomExercises(3, BODYBUILDING_ADDITIONAL_ADVANCED_PULL_EXERCISES)));

                trainingList.add(getTraining(Days.SATURDAY, PUSH_DAY_MUSCLE_GROUPS, 50,
                        BODYBUILDING_ADVANCED_PUSH_EXERCISES,
                        getRandomExercises(3, BODYBUILDING_ADDITIONAL_ADVANCED_PUSH_EXERCISES)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
            case EXPERT -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        BODYBUILDING_EXPERT_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsBodybuilding(user);

                trainingList.add(getTraining(Days.MONDAY, PULL_DAY_MUSCLE_GROUPS, 50,
                        BODYBUILDING_EXPERT_PULL_EXERCISES,
                        getRandomExercises(3, BODYBUILDING_ADDITIONAL_EXPERT_PULL_EXERCISES)));

                trainingList.add(getTraining(Days.TUESDAY, PUSH_DAY_MUSCLE_GROUPS, 50,
                        BODYBUILDING_EXPERT_PUSH_EXERCISES,
                        getRandomExercises(3, BODYBUILDING_ADDITIONAL_EXPERT_PUSH_EXERCISES)));

                trainingList.add(getTraining(Days.WEDNESDAY, LEG_DAY_MUSCLE_GROUPS, 50,
                        BODYBUILDING_EXPERT_LEG_EXERCISES,
                        getRandomExercises(3, BODYBUILDING_ADDITIONAL_EXPERT_LEG_EXERCISES)));

                trainingList.add(getTraining(Days.THURSDAY, PULL_DAY_MUSCLE_GROUPS, 50,
                        BODYBUILDING_EXPERT_PULL_EXERCISES,
                        getRandomExercises(3, BODYBUILDING_ADDITIONAL_EXPERT_PULL_EXERCISES)));

                trainingList.add(getTraining(Days.FRIDAY, PUSH_DAY_MUSCLE_GROUPS, 50,
                        BODYBUILDING_EXPERT_PUSH_EXERCISES,
                        getRandomExercises(3, BODYBUILDING_ADDITIONAL_EXPERT_PUSH_EXERCISES)));

                trainingList.add(getTraining(Days.SATURDAY, LEG_DAY_MUSCLE_GROUPS, 50,
                        BODYBUILDING_EXPERT_LEG_EXERCISES,
                        getRandomExercises(3, BODYBUILDING_ADDITIONAL_EXPERT_LEG_EXERCISES)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
            case PROFESSIONAL -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        BODYBUILDING_PROFESSIONAL_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsBodybuilding(user);

                trainingList.add(getTraining(Days.MONDAY, PULL_DAY_MUSCLE_GROUPS, 50,
                        BODYBUILDING_PROFESSIONAL_PULL_EXERCISES,
                        getRandomExercises(3, BODYBUILDING_ADDITIONAL_PROFESSIONAL_PULL_EXERCISES)));

                trainingList.add(getTraining(Days.TUESDAY, PUSH_DAY_MUSCLE_GROUPS, 50,
                        BODYBUILDING_PROFESSIONAL_PUSH_EXERCISES,
                        getRandomExercises(3, BODYBUILDING_ADDITIONAL_PROFESSIONAL_PUSH_EXERCISES)));

                trainingList.add(getTraining(Days.WEDNESDAY, LEG_DAY_MUSCLE_GROUPS, 50,
                        BODYBUILDING_PROFESSIONAL_LEG_EXERCISES,
                        getRandomExercises(3, BODYBUILDING_ADDITIONAL_PROFESSIONAL_LEG_EXERCISES)));

                trainingList.add(getTraining(Days.THURSDAY, PULL_DAY_MUSCLE_GROUPS, 50,
                        BODYBUILDING_PROFESSIONAL_PULL_EXERCISES,
                        getRandomExercises(3, BODYBUILDING_ADDITIONAL_PROFESSIONAL_PULL_EXERCISES)));

                trainingList.add(getTraining(Days.FRIDAY, PUSH_DAY_MUSCLE_GROUPS, 50,
                        BODYBUILDING_PROFESSIONAL_PUSH_EXERCISES,
                        getRandomExercises(3, BODYBUILDING_ADDITIONAL_PROFESSIONAL_PUSH_EXERCISES)));

                trainingList.add(getTraining(Days.SATURDAY, LEG_DAY_MUSCLE_GROUPS, 50,
                        BODYBUILDING_PROFESSIONAL_LEG_EXERCISES,
                        getRandomExercises(3, BODYBUILDING_ADDITIONAL_PROFESSIONAL_LEG_EXERCISES)));

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
                        POWERLIFTING_BEGINNER_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsPowerliftingAndOlympic(user);

                trainingList.add(getTraining(Days.MONDAY, PULL_DAY_MUSCLE_GROUPS, 110,
                        POWERLIFTING_BEGINNER_PULL_EXERCISES,
                        getRandomExercises(3, POWERLIFTING_ADDITIONAL_BEGINNER_PULL_EXERCISES)));

                trainingList.add(getTraining(Days.WEDNESDAY, PUSH_DAY_MUSCLE_GROUPS, 110,
                        POWERLIFTING_BEGINNER_PUSH_EXERCISES,
                        getRandomExercises(3, POWERLIFTING_ADDITIONAL_BEGINNER_PUSH_EXERCISES)));

                trainingList.add(getTraining(Days.FRIDAY, LEG_DAY_MUSCLE_GROUPS, 110,
                        POWERLIFTING_BEGINNER_LEG_EXERCISES,
                        getRandomExercises(3, POWERLIFTING_ADDITIONAL_BEGINNER_LEG_EXERCISES)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
            case INTERMEDIATE -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        POWERLIFTING_INTERMEDIATE_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsPowerliftingAndOlympic(user);

                trainingList.add(getTraining(Days.MONDAY, PULL_DAY_MUSCLE_GROUPS, 130,
                        POWERLIFTING_INTERMEDIATE_PULL_EXERCISES,
                        getRandomExercises(3, POWERLIFTING_ADDITIONAL_INTERMEDIATE_PULL_EXERCISES)));

                trainingList.add(getTraining(Days.WEDNESDAY, PUSH_DAY_MUSCLE_GROUPS, 130,
                        POWERLIFTING_INTERMEDIATE_PUSH_EXERCISES,
                        getRandomExercises(3, POWERLIFTING_ADDITIONAL_INTERMEDIATE_PUSH_EXERCISES)));

                trainingList.add(getTraining(Days.FRIDAY, LEG_DAY_MUSCLE_GROUPS, 130,
                        POWERLIFTING_INTERMEDIATE_LEG_EXERCISES,
                        getRandomExercises(3, POWERLIFTING_ADDITIONAL_INTERMEDIATE_LEG_EXERCISES)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
            case ADVANCED -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        POWERLIFTING_ADVANCED_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsPowerliftingAndOlympic(user);

                trainingList.add(getTraining(Days.MONDAY, PULL_DAY_MUSCLE_GROUPS, 130,
                        POWERLIFTING_ADVANCED_PULL_EXERCISES,
                        getRandomExercises(3, POWERLIFTING_ADDITIONAL_ADVANCED_PULL_EXERCISES)));

                trainingList.add(getTraining(Days.TUESDAY, PUSH_DAY_MUSCLE_GROUPS, 130,
                        POWERLIFTING_ADVANCED_PUSH_EXERCISES,
                        getRandomExercises(3, POWERLIFTING_ADDITIONAL_ADVANCED_PUSH_EXERCISES)));

                trainingList.add(getTraining(Days.THURSDAY, LEG_DAY_MUSCLE_GROUPS, 130,
                        POWERLIFTING_ADVANCED_LEG_EXERCISES,
                        getRandomExercises(3, POWERLIFTING_ADDITIONAL_ADVANCED_LEG_EXERCISES)));

                trainingList.add(getTraining(Days.SATURDAY, LEG_DAY_MUSCLE_GROUPS, 60,
                        POWERLIFTING_ADVANCED_FULL_EXERCISES,
                        getRandomExercises(2, POWERLIFTING_ADDITIONAL_ADVANCED_FULL_EXERCISES)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
            case EXPERT -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        POWERLIFTING_EXPERT_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsPowerliftingAndOlympic(user);

                trainingList.add(getTraining(Days.MONDAY, PULL_DAY_MUSCLE_GROUPS, 150,
                        POWERLIFTING_EXPERT_PULL_EXERCISES,
                        getRandomExercises(3, POWERLIFTING_ADDITIONAL_EXPERT_PULL_EXERCISES)));

                trainingList.add(getTraining(Days.TUESDAY, PUSH_DAY_MUSCLE_GROUPS, 150,
                        POWERLIFTING_EXPERT_PUSH_EXERCISES,
                        getRandomExercises(3, POWERLIFTING_ADDITIONAL_EXPERT_PUSH_EXERCISES)));

                trainingList.add(getTraining(Days.WEDNESDAY, LEG_DAY_MUSCLE_GROUPS, 150,
                        POWERLIFTING_EXPERT_LEG_EXERCISES,
                        getRandomExercises(3, POWERLIFTING_ADDITIONAL_EXPERT_LEG_EXERCISES)));

                trainingList.add(getTraining(Days.FRIDAY, PULL_DAY_MUSCLE_GROUPS, 150,
                        POWERLIFTING_EXPERT_PULL_EXERCISES,
                        getRandomExercises(3, POWERLIFTING_ADDITIONAL_EXPERT_PULL_EXERCISES)));

                trainingList.add(getTraining(Days.SATURDAY, PUSH_DAY_MUSCLE_GROUPS, 150,
                        POWERLIFTING_EXPERT_PUSH_EXERCISES,
                        getRandomExercises(3, POWERLIFTING_ADDITIONAL_EXPERT_PUSH_EXERCISES)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
            case PROFESSIONAL -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        POWERLIFTING_PROFESSIONAL_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);
                setWeightAndRepsPowerliftingAndOlympic(user);

                trainingList.add(getTraining(Days.MONDAY, PULL_DAY_MUSCLE_GROUPS, 150,
                        POWERLIFTING_PROFESSIONAL_PULL_EXERCISES,
                        getRandomExercises(3, POWERLIFTING_ADDITIONAL_PROFESSIONAL_PULL_EXERCISES)));

                trainingList.add(getTraining(Days.TUESDAY, PUSH_DAY_MUSCLE_GROUPS, 150,
                        POWERLIFTING_PROFESSIONAL_PUSH_EXERCISES,
                        getRandomExercises(3, POWERLIFTING_ADDITIONAL_PROFESSIONAL_PUSH_EXERCISES)));

                trainingList.add(getTraining(Days.WEDNESDAY, LEG_DAY_MUSCLE_GROUPS, 150,
                        POWERLIFTING_PROFESSIONAL_LEG_EXERCISES,
                        getRandomExercises(3, POWERLIFTING_ADDITIONAL_PROFESSIONAL_LEG_EXERCISES)));

                trainingList.add(getTraining(Days.THURSDAY, PULL_DAY_MUSCLE_GROUPS, 150,
                        POWERLIFTING_PROFESSIONAL_PULL_EXERCISES,
                        getRandomExercises(3, POWERLIFTING_ADDITIONAL_PROFESSIONAL_PULL_EXERCISES)));

                trainingList.add(getTraining(Days.FRIDAY, PUSH_DAY_MUSCLE_GROUPS, 150,
                        POWERLIFTING_PROFESSIONAL_PUSH_EXERCISES,
                        getRandomExercises(3, POWERLIFTING_ADDITIONAL_PROFESSIONAL_PUSH_EXERCISES)));

                trainingList.add(getTraining(Days.SATURDAY, LEG_DAY_MUSCLE_GROUPS, 150,
                        POWERLIFTING_PROFESSIONAL_LEG_EXERCISES,
                        getRandomExercises(3, POWERLIFTING_ADDITIONAL_PROFESSIONAL_LEG_EXERCISES)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
        }
    }

    @Override
    public void setWeightAndRepsPowerliftingAndOlympic(User user) {
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
                        CROSSFIT_BEGINNER_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsCrossfit(user);

                trainingList.add(getTraining(Days.MONDAY, FULL_BODY, 10,
                        CROSSFIT_BEGINNER_TRAINING_ONE,
                        getRandomExercises(3, CROSSFIT_ADDITIONAL_BEGINNER_TRAINING_ONE)));

                trainingList.add(getTraining(Days.WEDNESDAY, FULL_BODY, 10,
                        CROSSFIT_BEGINNER_TRAINING_TWO,
                        getRandomExercises(3, CROSSFIT_ADDITIONAL_BEGINNER_TRAINING_TWO)));

                trainingList.add(getTraining(Days.FRIDAY, FULL_BODY, 10,
                        CROSSFIT_BEGINNER_TRAINING_THREE,
                        getRandomExercises(3, CROSSFIT_ADDITIONAL_BEGINNER_TRAINING_THREE)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
            case INTERMEDIATE -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        CROSSFIT_INTERMEDIATE_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsCrossfit(user);

                trainingList.add(getTraining(Days.MONDAY, FULL_BODY, 10,
                        CROSSFIT_INTERMEDIATE_TRAINING_ONE,
                        getRandomExercises(3, CROSSFIT_ADDITIONAL_INTERMEDIATE_TRAINING_ONE)));

                trainingList.add(getTraining(Days.WEDNESDAY, FULL_BODY, 10,
                        CROSSFIT_INTERMEDIATE_TRAINING_TWO,
                        getRandomExercises(3, CROSSFIT_ADDITIONAL_INTERMEDIATE_TRAINING_TWO)));

                trainingList.add(getTraining(Days.FRIDAY, FULL_BODY, 10,
                        CROSSFIT_INTERMEDIATE_TRAINING_THREE,
                        getRandomExercises(3, CROSSFIT_ADDITIONAL_INTERMEDIATE_TRAINING_THREE)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
            case ADVANCED -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        CROSSFIT_ADVANCED_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsCrossfit(user);

                trainingList.add(getTraining(Days.MONDAY, FULL_BODY, 10,
                        CROSSFIT_ADVANCED_TRAINING_ONE,
                        getRandomExercises(3, CROSSFIT_ADDITIONAL_ADVANCED_TRAINING_ONE)));

                trainingList.add(getTraining(Days.WEDNESDAY, FULL_BODY, 10,
                        CROSSFIT_ADVANCED_TRAINING_TWO,
                        getRandomExercises(3, CROSSFIT_ADDITIONAL_ADVANCED_TRAINING_TWO)));

                trainingList.add(getTraining(Days.FRIDAY, FULL_BODY, 10,
                        CROSSFIT_ADVANCED_TRAINING_THREE,
                        getRandomExercises(3, CROSSFIT_ADDITIONAL_ADVANCED_TRAINING_THREE)));

                trainingList.add(getTraining(Days.FRIDAY, FULL_BODY, 10,
                        CROSSFIT_ADVANCED_TRAINING_FOUR,
                        getRandomExercises(3, CROSSFIT_ADDITIONAL_ADVANCED_TRAINING_FOUR)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
            case EXPERT -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        CROSSFIT_EXPERT_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsCrossfit(user);

                trainingList.add(getTraining(Days.MONDAY, FULL_BODY, 10,
                        CROSSFIT_EXPERT_TRAINING_ONE,
                        getRandomExercises(3, CROSSFIT_ADDITIONAL_EXPERT_TRAINING_ONE)));

                trainingList.add(getTraining(Days.WEDNESDAY, FULL_BODY, 10,
                        CROSSFIT_EXPERT_TRAINING_TWO,
                        getRandomExercises(3, CROSSFIT_ADDITIONAL_EXPERT_TRAINING_TWO)));

                trainingList.add(getTraining(Days.FRIDAY, FULL_BODY, 10,
                        CROSSFIT_EXPERT_TRAINING_THREE,
                        getRandomExercises(3, CROSSFIT_ADDITIONAL_EXPERT_TRAINING_THREE)));

                trainingList.add(getTraining(Days.FRIDAY, FULL_BODY, 10,
                        CROSSFIT_EXPERT_TRAINING_FOUR,
                        getRandomExercises(3, CROSSFIT_ADDITIONAL_EXPERT_TRAINING_FOUR)));

                trainingList.add(getTraining(Days.FRIDAY, FULL_BODY, 10,
                        CROSSFIT_EXPERT_TRAINING_FIVE,
                        getRandomExercises(3, CROSSFIT_ADDITIONAL_EXPERT_TRAINING_FIVE)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
            case PROFESSIONAL -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        CROSSFIT_PROFESSIONAL_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsCrossfit(user);

                trainingList.add(getTraining(Days.MONDAY, FULL_BODY, 10,
                        CROSSFIT_PROFESSIONAL_TRAINING_ONE,
                        getRandomExercises(3, CROSSFIT_ADDITIONAL_PROFESSIONAL_TRAINING_ONE)));

                trainingList.add(getTraining(Days.WEDNESDAY, FULL_BODY, 10,
                        CROSSFIT_PROFESSIONAL_TRAINING_TWO,
                        getRandomExercises(3, CROSSFIT_ADDITIONAL_PROFESSIONAL_TRAINING_TWO)));

                trainingList.add(getTraining(Days.FRIDAY, FULL_BODY, 10,
                        CROSSFIT_PROFESSIONAL_TRAINING_THREE,
                        getRandomExercises(3, CROSSFIT_ADDITIONAL_PROFESSIONAL_TRAINING_THREE)));

                trainingList.add(getTraining(Days.FRIDAY, FULL_BODY, 10,
                        CROSSFIT_PROFESSIONAL_TRAINING_FOUR,
                        getRandomExercises(3, CROSSFIT_ADDITIONAL_PROFESSIONAL_TRAINING_FOUR)));

                trainingList.add(getTraining(Days.FRIDAY, FULL_BODY, 10,
                        CROSSFIT_PROFESSIONAL_TRAINING_FIVE,
                        getRandomExercises(3, CROSSFIT_ADDITIONAL_PROFESSIONAL_TRAINING_FIVE)));

                trainingList.add(getTraining(Days.FRIDAY, FULL_BODY, 10,
                        CROSSFIT_PROFESSIONAL_TRAINING_SIX,
                        getRandomExercises(3, CROSSFIT_ADDITIONAL_PROFESSIONAL_TRAINING_SIX)));

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
        List<Training> trainingList = new ArrayList<>();
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        FitnessLevels userFitnessLevel = user.getLevel();
        List<Exercises> alreadyDoneExercises = exerciseService.getExercisesForUser(user);
        List<Exercises> neverDoneExercises;

        switch (userFitnessLevel) {
            case BEGINNER -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        OLYMPIC_BEGINNER_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsPowerliftingAndOlympic(user);

                trainingList.add(getTraining(Days.MONDAY, FULL_BODY, 110,
                        OLYMPIC_BEGINNER_TRAINING_ONE,
                        getRandomExercises(3, OLYMPIC_ADDITIONAL_BEGINNER_TRAINING_ONE)));

                trainingList.add(getTraining(Days.WEDNESDAY, FULL_BODY, 110,
                        OLYMPIC_BEGINNER_TRAINING_TWO,
                        getRandomExercises(3, OLYMPIC_ADDITIONAL_BEGINNER_TRAINING_TWO)));

                trainingList.add(getTraining(Days.FRIDAY, FULL_BODY, 110,
                        OLYMPIC_BEGINNER_TRAINING_THREE,
                        getRandomExercises(3, OLYMPIC_ADDITIONAL_BEGINNER_TRAINING_THREE)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
            case INTERMEDIATE -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        OLYMPIC_INTERMEDIATE_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsPowerliftingAndOlympic(user);

                trainingList.add(getTraining(Days.MONDAY, FULL_BODY, 110,
                        OLYMPIC_INTERMEDIATE_TRAINING_ONE,
                        getRandomExercises(3, OLYMPIC_ADDITIONAL_INTERMEDIATE_TRAINING_ONE)));

                trainingList.add(getTraining(Days.WEDNESDAY, FULL_BODY, 110,
                        OLYMPIC_INTERMEDIATE_TRAINING_TWO,
                        getRandomExercises(3, OLYMPIC_ADDITIONAL_INTERMEDIATE_TRAINING_TWO)));

                trainingList.add(getTraining(Days.FRIDAY, FULL_BODY, 110,
                        OLYMPIC_INTERMEDIATE_TRAINING_THREE,
                        getRandomExercises(3, OLYMPIC_ADDITIONAL_INTERMEDIATE_TRAINING_THREE)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
            case ADVANCED -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        OLYMPIC_ADVANCED_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsPowerliftingAndOlympic(user);

                trainingList.add(getTraining(Days.MONDAY, FULL_BODY, 110,
                        OLYMPIC_ADVANCED_TRAINING_ONE,
                        getRandomExercises(3, OLYMPIC_ADDITIONAL_ADVANCED_TRAINING_ONE)));

                trainingList.add(getTraining(Days.WEDNESDAY, FULL_BODY, 110,
                        OLYMPIC_ADVANCED_TRAINING_TWO,
                        getRandomExercises(3, OLYMPIC_ADDITIONAL_ADVANCED_TRAINING_TWO)));

                trainingList.add(getTraining(Days.FRIDAY, FULL_BODY, 110,
                        OLYMPIC_ADVANCED_TRAINING_THREE,
                        getRandomExercises(3, OLYMPIC_ADDITIONAL_ADVANCED_TRAINING_THREE)));

                trainingList.add(getTraining(Days.FRIDAY, FULL_BODY, 110,
                        OLYMPIC_ADVANCED_TRAINING_FOUR,
                        getRandomExercises(3, OLYMPIC_ADDITIONAL_ADVANCED_TRAINING_FOUR)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
            case EXPERT -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        OLYMPIC_EXPERT_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsPowerliftingAndOlympic(user);

                trainingList.add(getTraining(Days.MONDAY, FULL_BODY, 110,
                        OLYMPIC_EXPERT_TRAINING_ONE,
                        getRandomExercises(3, OLYMPIC_ADDITIONAL_EXPERT_TRAINING_ONE)));

                trainingList.add(getTraining(Days.WEDNESDAY, FULL_BODY, 110,
                        OLYMPIC_EXPERT_TRAINING_TWO,
                        getRandomExercises(3, OLYMPIC_ADDITIONAL_EXPERT_TRAINING_TWO)));

                trainingList.add(getTraining(Days.FRIDAY, FULL_BODY, 110,
                        OLYMPIC_EXPERT_TRAINING_THREE,
                        getRandomExercises(3, OLYMPIC_ADDITIONAL_EXPERT_TRAINING_THREE)));

                trainingList.add(getTraining(Days.FRIDAY, FULL_BODY, 110,
                        OLYMPIC_EXPERT_TRAINING_FOUR,
                        getRandomExercises(3, OLYMPIC_ADDITIONAL_EXPERT_TRAINING_FOUR)));

                trainingList.add(getTraining(Days.FRIDAY, FULL_BODY, 110,
                        OLYMPIC_EXPERT_TRAINING_FIVE,
                        getRandomExercises(3, OLYMPIC_ADDITIONAL_EXPERT_TRAINING_FIVE)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
            case PROFESSIONAL -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        OLYMPIC_PROFESSIONAL_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsPowerliftingAndOlympic(user);

                trainingList.add(getTraining(Days.MONDAY, FULL_BODY, 110,
                        OLYMPIC_PROFESSIONAL_TRAINING_ONE,
                        getRandomExercises(3, OLYMPIC_ADDITIONAL_PROFESSIONAL_TRAINING_ONE)));

                trainingList.add(getTraining(Days.WEDNESDAY, FULL_BODY, 110,
                        OLYMPIC_PROFESSIONAL_TRAINING_TWO,
                        getRandomExercises(3, OLYMPIC_ADDITIONAL_PROFESSIONAL_TRAINING_TWO)));

                trainingList.add(getTraining(Days.FRIDAY, FULL_BODY, 110,
                        OLYMPIC_PROFESSIONAL_TRAINING_THREE,
                        getRandomExercises(3, OLYMPIC_ADDITIONAL_PROFESSIONAL_TRAINING_THREE)));

                trainingList.add(getTraining(Days.FRIDAY, FULL_BODY, 110,
                        OLYMPIC_PROFESSIONAL_TRAINING_FOUR,
                        getRandomExercises(3, OLYMPIC_ADDITIONAL_PROFESSIONAL_TRAINING_FOUR)));

                trainingList.add(getTraining(Days.FRIDAY, FULL_BODY, 110,
                        OLYMPIC_PROFESSIONAL_TRAINING_FIVE,
                        getRandomExercises(3, OLYMPIC_ADDITIONAL_PROFESSIONAL_TRAINING_FIVE)));

                trainingList.add(getTraining(Days.FRIDAY, FULL_BODY, 110,
                        OLYMPIC_PROFESSIONAL_TRAINING_SIX,
                        getRandomExercises(3, OLYMPIC_ADDITIONAL_PROFESSIONAL_TRAINING_SIX)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
        }
    }

    @Override
    public void generateCardioPlan(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        FitnessLevels userFitnessLevel = user.getLevel();
        List<Training> trainingList = new ArrayList<>();

        switch (userFitnessLevel) {
            case BEGINNER -> {
                trainingList.add(getTraining(Days.MONDAY, FULL_BODY, 110,
                        CARDIO_BEGINNER_TRAINING_ONE,
                        getRandomExercises(3, CARDIO_ADDITIONAL_BEGINNER_TRAINING_ONE)));

                trainingList.add(getTraining(Days.WEDNESDAY, FULL_BODY, 110,
                        CARDIO_BEGINNER_TRAINING_TWO,
                        getRandomExercises(3, CARDIO_ADDITIONAL_BEGINNER_TRAINING_TWO)));

                trainingList.add(getTraining(Days.FRIDAY, FULL_BODY, 110,
                        CARDIO_BEGINNER_TRAINING_THREE,
                        getRandomExercises(3, CARDIO_ADDITIONAL_BEGINNER_TRAINING_THREE)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
            case INTERMEDIATE -> {
                trainingList.add(getTraining(Days.MONDAY, FULL_BODY, 110,
                        CARDIO_INTERMEDIATE_TRAINING_ONE,
                        getRandomExercises(3, CARDIO_ADDITIONAL_INTERMEDIATE_TRAINING_ONE)));

                trainingList.add(getTraining(Days.WEDNESDAY, FULL_BODY, 110,
                        CARDIO_INTERMEDIATE_TRAINING_TWO,
                        getRandomExercises(3, CARDIO_ADDITIONAL_INTERMEDIATE_TRAINING_TWO)));

                trainingList.add(getTraining(Days.FRIDAY, FULL_BODY, 110,
                        CARDIO_INTERMEDIATE_TRAINING_THREE,
                        getRandomExercises(3, CARDIO_ADDITIONAL_INTERMEDIATE_TRAINING_THREE)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
            case ADVANCED -> {
                trainingList.add(getTraining(Days.MONDAY, FULL_BODY, 110,
                        CARDIO_ADVANCED_TRAINING_ONE,
                        getRandomExercises(3, CARDIO_ADDITIONAL_ADVANCED_TRAINING_ONE)));

                trainingList.add(getTraining(Days.WEDNESDAY, FULL_BODY, 110,
                        CARDIO_ADVANCED_TRAINING_TWO,
                        getRandomExercises(3, CARDIO_ADDITIONAL_ADVANCED_TRAINING_TWO)));

                trainingList.add(getTraining(Days.FRIDAY, FULL_BODY, 110,
                        CARDIO_ADVANCED_TRAINING_THREE,
                        getRandomExercises(3, CARDIO_ADDITIONAL_ADVANCED_TRAINING_THREE)));

                trainingList.add(getTraining(Days.FRIDAY, FULL_BODY, 110,
                        CARDIO_ADVANCED_TRAINING_FOUR,
                        getRandomExercises(3, CARDIO_ADDITIONAL_ADVANCED_TRAINING_FOUR)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
            case EXPERT -> {
                trainingList.add(getTraining(Days.MONDAY, FULL_BODY, 110,
                        CARDIO_EXPERT_TRAINING_ONE,
                        getRandomExercises(3, CARDIO_ADDITIONAL_EXPERT_TRAINING_ONE)));

                trainingList.add(getTraining(Days.WEDNESDAY, FULL_BODY, 110,
                        CARDIO_EXPERT_TRAINING_TWO,
                        getRandomExercises(3, CARDIO_ADDITIONAL_EXPERT_TRAINING_TWO)));

                trainingList.add(getTraining(Days.FRIDAY, FULL_BODY, 110,
                        CARDIO_EXPERT_TRAINING_THREE,
                        getRandomExercises(3, CARDIO_ADDITIONAL_EXPERT_TRAINING_THREE)));

                trainingList.add(getTraining(Days.FRIDAY, FULL_BODY, 110,
                        CARDIO_EXPERT_TRAINING_FOUR,
                        getRandomExercises(3, CARDIO_ADDITIONAL_EXPERT_TRAINING_FOUR)));

                trainingList.add(getTraining(Days.FRIDAY, FULL_BODY, 110,
                        CARDIO_EXPERT_TRAINING_FIVE,
                        getRandomExercises(3, CARDIO_ADDITIONAL_EXPERT_TRAINING_FIVE)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
            case PROFESSIONAL -> {
                trainingList.add(getTraining(Days.MONDAY, FULL_BODY, 110,
                        CARDIO_PROFESSIONAL_TRAINING_ONE,
                        getRandomExercises(3, CARDIO_ADDITIONAL_PROFESSIONAL_TRAINING_ONE)));

                trainingList.add(getTraining(Days.WEDNESDAY, FULL_BODY, 110,
                        CARDIO_PROFESSIONAL_TRAINING_TWO,
                        getRandomExercises(3, CARDIO_ADDITIONAL_PROFESSIONAL_TRAINING_TWO)));

                trainingList.add(getTraining(Days.FRIDAY, FULL_BODY, 110,
                        CARDIO_PROFESSIONAL_TRAINING_THREE,
                        getRandomExercises(3, CARDIO_ADDITIONAL_PROFESSIONAL_TRAINING_THREE)));

                trainingList.add(getTraining(Days.FRIDAY, FULL_BODY, 110,
                        CARDIO_PROFESSIONAL_TRAINING_FOUR,
                        getRandomExercises(3, CARDIO_ADDITIONAL_PROFESSIONAL_TRAINING_FOUR)));

                trainingList.add(getTraining(Days.FRIDAY, FULL_BODY, 110,
                        CARDIO_PROFESSIONAL_TRAINING_FIVE,
                        getRandomExercises(3, CARDIO_ADDITIONAL_PROFESSIONAL_TRAINING_FIVE)));

                trainingList.add(getTraining(Days.FRIDAY, FULL_BODY, 110,
                        CARDIO_PROFESSIONAL_TRAINING_SIX,
                        getRandomExercises(3, CARDIO_ADDITIONAL_PROFESSIONAL_TRAINING_SIX)));

                user.setTrainingPlan(trainingList);
                userRepository.save(user);
            }
        }
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