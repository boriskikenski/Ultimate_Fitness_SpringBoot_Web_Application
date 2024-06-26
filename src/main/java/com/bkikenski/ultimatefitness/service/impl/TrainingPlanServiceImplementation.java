package com.bkikenski.ultimatefitness.service.impl;

import com.bkikenski.ultimatefitness.model.entity.Exercise;
import com.bkikenski.ultimatefitness.model.entity.Training;
import com.bkikenski.ultimatefitness.model.entity.User;
import com.bkikenski.ultimatefitness.model.enumerations.*;
import com.bkikenski.ultimatefitness.model.exceptions.UserNotFoundException;
import com.bkikenski.ultimatefitness.repository.ExerciseRepository;
import com.bkikenski.ultimatefitness.repository.TrainingRepository;
import com.bkikenski.ultimatefitness.repository.UserRepository;
import com.bkikenski.ultimatefitness.service.ExerciseService;
import com.bkikenski.ultimatefitness.service.TrainingPlanService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.bkikenski.ultimatefitness.model.util.TrainingUtils.*;

@Service
public class TrainingPlanServiceImplementation implements TrainingPlanService {
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;
    private final TrainingRepository trainingRepository;
    private final ExerciseService exerciseService;

    public TrainingPlanServiceImplementation(UserRepository userRepository, ExerciseRepository exerciseRepository,
                                             TrainingRepository trainingRepository, ExerciseService exerciseService) {
        this.userRepository = userRepository;
        this.exerciseRepository = exerciseRepository;
        this.trainingRepository = trainingRepository;
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
        List<ExercisesConstants> alreadyDoneExercises = exerciseService.getExercisesForUser(user);
        List<ExercisesConstants> neverDoneExercises;

        switch (userFitnessLevel) {
            case BEGINNER -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        BODYBUILDING_BEGINNER_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsBodybuilding(user);

                getTraining(Days.MONDAY, PULL_DAY_MUSCLE_GROUPS, 60,
                        BODYBUILDING_BEGINNER_PULL_EXERCISES,
                        getRandomExercises(3, BODYBUILDING_ADDITIONAL_BEGINNER_PULL_EXERCISES), user);

                getTraining(Days.WEDNESDAY, PUSH_DAY_MUSCLE_GROUPS, 60,
                        BODYBUILDING_BEGINNER_PUSH_EXERCISES,
                        getRandomExercises(3, BODYBUILDING_ADDITIONAL_BEGINNER_PUSH_EXERCISES), user);

                getTraining(Days.FRIDAY, LEG_DAY_MUSCLE_GROUPS, 60,
                        BODYBUILDING_BEGINNER_LEG_EXERCISES,
                        getRandomExercises(3, BODYBUILDING_ADDITIONAL_BEGINNER_LEG_EXERCISES), user);

            }
            case INTERMEDIATE -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        BODYBUILDING_INTERMEDIATE_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsBodybuilding(user);

                getTraining(Days.MONDAY, PULL_DAY_MUSCLE_GROUPS, 60,
                        BODYBUILDING_INTERMEDIATE_PULL_EXERCISES,
                        getRandomExercises(2, BODYBUILDING_ADDITIONAL_INTERMEDIATE_PULL_EXERCISES), user);

                getTraining(Days.TUESDAY, PUSH_DAY_MUSCLE_GROUPS, 60,
                        BODYBUILDING_INTERMEDIATE_PUSH_EXERCISES,
                        getRandomExercises(2, BODYBUILDING_ADDITIONAL_INTERMEDIATE_PUSH_EXERCISES), user);

                getTraining(Days.THURSDAY, LEG_DAY_MUSCLE_GROUPS, 60,
                        BODYBUILDING_INTERMEDIATE_LEG_EXERCISES,
                        getRandomExercises(2, BODYBUILDING_ADDITIONAL_INTERMEDIATE_LEG_EXERCISES), user);

                getTraining(Days.SATURDAY, LEG_DAY_MUSCLE_GROUPS, 60,
                        BODYBUILDING_INTERMEDIATE_FULL_EXERCISES,
                        getRandomExercises(2, BODYBUILDING_ADDITIONAL_INTERMEDIATE_FULL_EXERCISES), user);

            }
            case ADVANCED -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        BODYBUILDING_ADVANCED_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsBodybuilding(user);

                getTraining(Days.MONDAY, PULL_DAY_MUSCLE_GROUPS, 50,
                        BODYBUILDING_ADVANCED_PULL_EXERCISES,
                        getRandomExercises(2, BODYBUILDING_ADDITIONAL_ADVANCED_PULL_EXERCISES), user);

                getTraining(Days.TUESDAY, PUSH_DAY_MUSCLE_GROUPS, 50,
                        BODYBUILDING_ADVANCED_PUSH_EXERCISES,
                        getRandomExercises(1, BODYBUILDING_ADDITIONAL_ADVANCED_PUSH_EXERCISES), user);

                getTraining(Days.WEDNESDAY, LEG_DAY_MUSCLE_GROUPS, 50,
                        BODYBUILDING_ADVANCED_LEG_EXERCISES,
                        getRandomExercises(1, BODYBUILDING_ADDITIONAL_ADVANCED_LEG_EXERCISES), user);

                getTraining(Days.FRIDAY, PULL_DAY_MUSCLE_GROUPS, 50,
                        BODYBUILDING_ADVANCED_PULL_EXERCISES,
                        getRandomExercises(1, BODYBUILDING_ADDITIONAL_ADVANCED_PULL_EXERCISES), user);

                getTraining(Days.SATURDAY, PUSH_DAY_MUSCLE_GROUPS, 50,
                        BODYBUILDING_ADVANCED_PUSH_EXERCISES,
                        getRandomExercises(1, BODYBUILDING_ADDITIONAL_ADVANCED_PUSH_EXERCISES), user);
            }
            case EXPERT -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        BODYBUILDING_EXPERT_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsBodybuilding(user);

                getTraining(Days.MONDAY, PULL_DAY_MUSCLE_GROUPS, 50,
                        BODYBUILDING_EXPERT_PULL_EXERCISES,
                        getRandomExercises(2, BODYBUILDING_ADDITIONAL_EXPERT_PULL_EXERCISES), user);

                getTraining(Days.TUESDAY, PUSH_DAY_MUSCLE_GROUPS, 50,
                        BODYBUILDING_EXPERT_PUSH_EXERCISES,
                        getRandomExercises(2, BODYBUILDING_ADDITIONAL_EXPERT_PUSH_EXERCISES), user);

                getTraining(Days.WEDNESDAY, LEG_DAY_MUSCLE_GROUPS, 50,
                        BODYBUILDING_EXPERT_LEG_EXERCISES,
                        getRandomExercises(2, BODYBUILDING_ADDITIONAL_EXPERT_LEG_EXERCISES), user);

                getTraining(Days.THURSDAY, PULL_DAY_MUSCLE_GROUPS, 50,
                        BODYBUILDING_EXPERT_PULL_EXERCISES,
                        getRandomExercises(2, BODYBUILDING_ADDITIONAL_EXPERT_PULL_EXERCISES), user);

                getTraining(Days.FRIDAY, PUSH_DAY_MUSCLE_GROUPS, 50,
                        BODYBUILDING_EXPERT_PUSH_EXERCISES,
                        getRandomExercises(2, BODYBUILDING_ADDITIONAL_EXPERT_PUSH_EXERCISES), user);

                getTraining(Days.SATURDAY, LEG_DAY_MUSCLE_GROUPS, 50,
                        BODYBUILDING_EXPERT_LEG_EXERCISES,
                        getRandomExercises(2, BODYBUILDING_ADDITIONAL_EXPERT_LEG_EXERCISES), user);

            }
            case PROFESSIONAL -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        BODYBUILDING_PROFESSIONAL_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsBodybuilding(user);

                getTraining(Days.MONDAY, PULL_DAY_MUSCLE_GROUPS, 50,
                        BODYBUILDING_PROFESSIONAL_PULL_EXERCISES,
                        getRandomExercises(2, BODYBUILDING_ADDITIONAL_PROFESSIONAL_PULL_EXERCISES), user);

                getTraining(Days.TUESDAY, PUSH_DAY_MUSCLE_GROUPS, 50,
                        BODYBUILDING_PROFESSIONAL_PUSH_EXERCISES,
                        getRandomExercises(2, BODYBUILDING_ADDITIONAL_PROFESSIONAL_PUSH_EXERCISES), user);

                getTraining(Days.WEDNESDAY, LEG_DAY_MUSCLE_GROUPS, 50,
                        BODYBUILDING_PROFESSIONAL_LEG_EXERCISES,
                        getRandomExercises(2, BODYBUILDING_ADDITIONAL_PROFESSIONAL_LEG_EXERCISES), user);

                getTraining(Days.THURSDAY, PULL_DAY_MUSCLE_GROUPS, 50,
                        BODYBUILDING_PROFESSIONAL_PULL_EXERCISES,
                        getRandomExercises(2, BODYBUILDING_ADDITIONAL_PROFESSIONAL_PULL_EXERCISES), user);

                getTraining(Days.FRIDAY, PUSH_DAY_MUSCLE_GROUPS, 50,
                        BODYBUILDING_PROFESSIONAL_PUSH_EXERCISES,
                        getRandomExercises(2, BODYBUILDING_ADDITIONAL_PROFESSIONAL_PUSH_EXERCISES), user);

                getTraining(Days.SATURDAY, LEG_DAY_MUSCLE_GROUPS, 50,
                        BODYBUILDING_PROFESSIONAL_LEG_EXERCISES,
                        getRandomExercises(2, BODYBUILDING_ADDITIONAL_PROFESSIONAL_LEG_EXERCISES), user);

            }
        }
    }

    @Override
    public void setWeightAndRepsBodybuilding(User user) {
        List<Exercise> exercises = user.getResults().get(user.getResults().size() - 1).getExercisesResults();

        for (Exercise exercise : exercises) {
            int lastWorkingReps = exercise.getLastTrainingRepsPerSet().stream().mapToInt(Integer::intValue).sum();
            List<Integer> reps = new ArrayList<>();

            if (lastWorkingReps < 32) {
                reps.add(8);
                reps.add(8);
                reps.add(8);
                reps.add(8);
                exercise.setNextExceptedRepsPerSet(reps);
            }
            else if (lastWorkingReps < 34) {
                reps.add(9);
                reps.add(9);
                reps.add(8);
                reps.add(8);
                exercise.setNextExceptedRepsPerSet(reps);
            }
            else if (lastWorkingReps < 36) {
                reps.add(9);
                reps.add(9);
                reps.add(9);
                reps.add(9);
                exercise.setNextExceptedRepsPerSet(reps);
            }
            else if (lastWorkingReps < 38) {
                reps.add(10);
                reps.add(10);
                reps.add(9);
                reps.add(9);
                exercise.setNextExceptedRepsPerSet(reps);
            }
            else if (lastWorkingReps < 40) {
                reps.add(10);
                reps.add(10);
                reps.add(10);
                reps.add(10);
                exercise.setNextExceptedRepsPerSet(reps);
            }
            else if (lastWorkingReps < 42) {
                reps.add(11);
                reps.add(11);
                reps.add(10);
                reps.add(10);
                exercise.setNextExceptedRepsPerSet(reps);
            }
            else if (lastWorkingReps < 44) {
                reps.add(11);
                reps.add(11);
                reps.add(11);
                reps.add(11);
                exercise.setNextExceptedRepsPerSet(reps);
            }
            else if (lastWorkingReps < 46) {
                reps.add(12);
                reps.add(12);
                reps.add(11);
                reps.add(11);
                exercise.setNextExceptedRepsPerSet(reps);
            }
            else if (lastWorkingReps < 48) {
                reps.add(12);
                reps.add(12);
                reps.add(12);
                reps.add(12);
                exercise.setNextExceptedRepsPerSet(reps);
            }
            else {
                float currentWeight = exercise.getCurrentWorkingWeight();
                exercise.getPreviousWeights().add(currentWeight);
                exercise.setCurrentWorkingWeight(currentWeight + 2.5f);
                reps.add(8);
                reps.add(8);
                reps.add(8);
                reps.add(8);
                exercise.setNextExceptedRepsPerSet(reps);
            }

            exerciseRepository.save(exercise);
        }

        userRepository.save(user);
    }

    @Override
    public void generatePowerliftingPlan(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        FitnessLevels userFitnessLevel = user.getLevel();
        List<ExercisesConstants> alreadyDoneExercises = exerciseService.getExercisesForUser(user);
        List<ExercisesConstants> neverDoneExercises;

        switch (userFitnessLevel) {
            case BEGINNER -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        POWERLIFTING_BEGINNER_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsPowerliftingAndOlympic(user);

                getTraining(Days.MONDAY, PULL_DAY_MUSCLE_GROUPS, 110,
                        POWERLIFTING_BEGINNER_PULL_EXERCISES,
                        getRandomExercises(1, POWERLIFTING_ADDITIONAL_BEGINNER_PULL_EXERCISES), user);

                getTraining(Days.WEDNESDAY, PUSH_DAY_MUSCLE_GROUPS, 110,
                        POWERLIFTING_BEGINNER_PUSH_EXERCISES,
                        getRandomExercises(1, POWERLIFTING_ADDITIONAL_BEGINNER_PUSH_EXERCISES), user);

                getTraining(Days.FRIDAY, LEG_DAY_MUSCLE_GROUPS, 110,
                        POWERLIFTING_BEGINNER_LEG_EXERCISES,
                        getRandomExercises(1, POWERLIFTING_ADDITIONAL_BEGINNER_LEG_EXERCISES), user);

            }
            case INTERMEDIATE -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        POWERLIFTING_INTERMEDIATE_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsPowerliftingAndOlympic(user);

                getTraining(Days.MONDAY, PULL_DAY_MUSCLE_GROUPS, 130,
                        POWERLIFTING_INTERMEDIATE_PULL_EXERCISES,
                        getRandomExercises(1, POWERLIFTING_ADDITIONAL_INTERMEDIATE_PULL_EXERCISES), user);

                getTraining(Days.WEDNESDAY, PUSH_DAY_MUSCLE_GROUPS, 130,
                        POWERLIFTING_INTERMEDIATE_PUSH_EXERCISES,
                        getRandomExercises(1, POWERLIFTING_ADDITIONAL_INTERMEDIATE_PUSH_EXERCISES), user);

                getTraining(Days.FRIDAY, LEG_DAY_MUSCLE_GROUPS, 130,
                        POWERLIFTING_INTERMEDIATE_LEG_EXERCISES,
                        getRandomExercises(1, POWERLIFTING_ADDITIONAL_INTERMEDIATE_LEG_EXERCISES), user);
            }
            case ADVANCED -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        POWERLIFTING_ADVANCED_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsPowerliftingAndOlympic(user);

                getTraining(Days.MONDAY, PULL_DAY_MUSCLE_GROUPS, 130,
                        POWERLIFTING_ADVANCED_PULL_EXERCISES,
                        getRandomExercises(1, POWERLIFTING_ADDITIONAL_ADVANCED_PULL_EXERCISES), user);

                getTraining(Days.TUESDAY, PUSH_DAY_MUSCLE_GROUPS, 130,
                        POWERLIFTING_ADVANCED_PUSH_EXERCISES,
                        getRandomExercises(1, POWERLIFTING_ADDITIONAL_ADVANCED_PUSH_EXERCISES), user);

                getTraining(Days.THURSDAY, LEG_DAY_MUSCLE_GROUPS, 130,
                        POWERLIFTING_ADVANCED_LEG_EXERCISES,
                        getRandomExercises(1, POWERLIFTING_ADDITIONAL_ADVANCED_LEG_EXERCISES), user);

                getTraining(Days.SATURDAY, LEG_DAY_MUSCLE_GROUPS, 130,
                        POWERLIFTING_ADVANCED_FULL_EXERCISES,
                        getRandomExercises(1, POWERLIFTING_ADDITIONAL_ADVANCED_FULL_EXERCISES), user);
            }
            case EXPERT -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        POWERLIFTING_EXPERT_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsPowerliftingAndOlympic(user);

                getTraining(Days.MONDAY, PULL_DAY_MUSCLE_GROUPS, 150,
                        POWERLIFTING_EXPERT_PULL_EXERCISES,
                        getRandomExercises(2, POWERLIFTING_ADDITIONAL_EXPERT_PULL_EXERCISES), user);

                getTraining(Days.TUESDAY, PUSH_DAY_MUSCLE_GROUPS, 150,
                        POWERLIFTING_EXPERT_PUSH_EXERCISES,
                        getRandomExercises(2, POWERLIFTING_ADDITIONAL_EXPERT_PUSH_EXERCISES), user);

                getTraining(Days.WEDNESDAY, LEG_DAY_MUSCLE_GROUPS, 150,
                        POWERLIFTING_EXPERT_LEG_EXERCISES,
                        getRandomExercises(2, POWERLIFTING_ADDITIONAL_EXPERT_LEG_EXERCISES), user);

                getTraining(Days.FRIDAY, PULL_DAY_MUSCLE_GROUPS, 150,
                        POWERLIFTING_EXPERT_PULL_EXERCISES,
                        getRandomExercises(2, POWERLIFTING_ADDITIONAL_EXPERT_PULL_EXERCISES), user);

                getTraining(Days.SATURDAY, PUSH_DAY_MUSCLE_GROUPS, 150,
                        POWERLIFTING_EXPERT_PUSH_EXERCISES,
                        getRandomExercises(2, POWERLIFTING_ADDITIONAL_EXPERT_PUSH_EXERCISES), user);

            }
            case PROFESSIONAL -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        POWERLIFTING_PROFESSIONAL_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);
                setWeightAndRepsPowerliftingAndOlympic(user);

                getTraining(Days.MONDAY, PULL_DAY_MUSCLE_GROUPS, 150,
                        POWERLIFTING_PROFESSIONAL_PULL_EXERCISES,
                        getRandomExercises(2, POWERLIFTING_ADDITIONAL_PROFESSIONAL_PULL_EXERCISES), user);

                getTraining(Days.TUESDAY, PUSH_DAY_MUSCLE_GROUPS, 150,
                        POWERLIFTING_PROFESSIONAL_PUSH_EXERCISES,
                        getRandomExercises(2, POWERLIFTING_ADDITIONAL_PROFESSIONAL_PUSH_EXERCISES), user);

                getTraining(Days.WEDNESDAY, LEG_DAY_MUSCLE_GROUPS, 150,
                        POWERLIFTING_PROFESSIONAL_LEG_EXERCISES,
                        getRandomExercises(2, POWERLIFTING_ADDITIONAL_PROFESSIONAL_LEG_EXERCISES), user);

                getTraining(Days.THURSDAY, PULL_DAY_MUSCLE_GROUPS, 150,
                        POWERLIFTING_PROFESSIONAL_PULL_EXERCISES,
                        getRandomExercises(2, POWERLIFTING_ADDITIONAL_PROFESSIONAL_PULL_EXERCISES), user);

                getTraining(Days.FRIDAY, PUSH_DAY_MUSCLE_GROUPS, 150,
                        POWERLIFTING_PROFESSIONAL_PUSH_EXERCISES,
                        getRandomExercises(2, POWERLIFTING_ADDITIONAL_PROFESSIONAL_PUSH_EXERCISES), user);

                getTraining(Days.SATURDAY, LEG_DAY_MUSCLE_GROUPS, 150,
                        POWERLIFTING_PROFESSIONAL_LEG_EXERCISES,
                        getRandomExercises(2, POWERLIFTING_ADDITIONAL_PROFESSIONAL_LEG_EXERCISES), user);
            }
        }
    }

    @Override
    public void setWeightAndRepsPowerliftingAndOlympic(User user) {
        List<Exercise> exercises = user.getResults().get(user.getResults().size() - 1).getExercisesResults();

        for (Exercise exercise : exercises) {
            int lastWorkingReps = exercise.getLastTrainingRepsPerSet().stream().mapToInt(Integer::intValue).sum();

            List<Integer> reps = new ArrayList<>();
            if (lastWorkingReps < 15) {
                reps.add(3);
                reps.add(3);
                reps.add(3);
                reps.add(3);
                reps.add(3);
                exercise.setNextExceptedRepsPerSet(reps);
            }
            else if (lastWorkingReps < 18) {
                reps.add(4);
                reps.add(4);
                reps.add(4);
                reps.add(3);
                reps.add(3);
                exercise.setNextExceptedRepsPerSet(reps);
            }
            else if (lastWorkingReps < 20) {
                reps.add(4);
                reps.add(4);
                reps.add(4);
                reps.add(4);
                reps.add(4);
                exercise.setNextExceptedRepsPerSet(reps);
            }
            else if (lastWorkingReps < 23) {
                reps.add(5);
                reps.add(5);
                reps.add(5);
                reps.add(4);
                reps.add(4);
                exercise.setNextExceptedRepsPerSet(reps);
            }
            else {
                float currentWeight = exercise.getCurrentWorkingWeight();
                exercise.getPreviousWeights().add(currentWeight);
                exercise.setCurrentWorkingWeight(currentWeight + 2.5f);
                reps.add(3);
                reps.add(3);
                reps.add(3);
                reps.add(3);
                reps.add(3);
                exercise.setNextExceptedRepsPerSet(reps);
            }
            exerciseRepository.save(exercise);
        }

        userRepository.save(user);
    }

    @Override
    public void generateCrossfitPlan(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        FitnessLevels userFitnessLevel = user.getLevel();
        List<ExercisesConstants> alreadyDoneExercises = exerciseService.getExercisesForUser(user);
        List<ExercisesConstants> neverDoneExercises;

        switch (userFitnessLevel) {
            case BEGINNER -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        CROSSFIT_BEGINNER_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsCrossfit(user);

                getTraining(Days.MONDAY, FULL_BODY, 10,
                        CROSSFIT_BEGINNER_TRAINING_ONE,
                        getRandomExercises(1, CROSSFIT_ADDITIONAL_BEGINNER_TRAINING_ONE), user);

                getTraining(Days.WEDNESDAY, FULL_BODY, 10,
                        CROSSFIT_BEGINNER_TRAINING_TWO,
                        getRandomExercises(0, CROSSFIT_ADDITIONAL_BEGINNER_TRAINING_TWO), user);

                getTraining(Days.FRIDAY, FULL_BODY, 10,
                        CROSSFIT_BEGINNER_TRAINING_THREE,
                        getRandomExercises(1, CROSSFIT_ADDITIONAL_BEGINNER_TRAINING_THREE), user);
            }
            case INTERMEDIATE -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        CROSSFIT_INTERMEDIATE_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsCrossfit(user);

                getTraining(Days.MONDAY, FULL_BODY, 10,
                        CROSSFIT_INTERMEDIATE_TRAINING_ONE,
                        getRandomExercises(1, CROSSFIT_ADDITIONAL_INTERMEDIATE_TRAINING_ONE), user);

                getTraining(Days.WEDNESDAY, FULL_BODY, 10,
                        CROSSFIT_INTERMEDIATE_TRAINING_TWO,
                        getRandomExercises(0, CROSSFIT_ADDITIONAL_INTERMEDIATE_TRAINING_TWO), user);

                getTraining(Days.FRIDAY, FULL_BODY, 10,
                        CROSSFIT_INTERMEDIATE_TRAINING_THREE,
                        getRandomExercises(1, CROSSFIT_ADDITIONAL_INTERMEDIATE_TRAINING_THREE), user);
            }
            case ADVANCED -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        CROSSFIT_ADVANCED_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsCrossfit(user);

                getTraining(Days.MONDAY, FULL_BODY, 10,
                        CROSSFIT_ADVANCED_TRAINING_ONE,
                        getRandomExercises(2, CROSSFIT_ADDITIONAL_ADVANCED_TRAINING_ONE), user);

                getTraining(Days.WEDNESDAY, FULL_BODY, 10,
                        CROSSFIT_ADVANCED_TRAINING_TWO,
                        getRandomExercises(0, CROSSFIT_ADDITIONAL_ADVANCED_TRAINING_TWO), user);

                getTraining(Days.FRIDAY, FULL_BODY, 10,
                        CROSSFIT_ADVANCED_TRAINING_THREE,
                        getRandomExercises(2, CROSSFIT_ADDITIONAL_ADVANCED_TRAINING_THREE), user);

                getTraining(Days.FRIDAY, FULL_BODY, 10,
                        CROSSFIT_ADVANCED_TRAINING_FOUR,
                        getRandomExercises(2, CROSSFIT_ADDITIONAL_ADVANCED_TRAINING_FOUR), user);
            }
            case EXPERT -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        CROSSFIT_EXPERT_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsCrossfit(user);

                getTraining(Days.MONDAY, FULL_BODY, 10,
                        CROSSFIT_EXPERT_TRAINING_ONE,
                        getRandomExercises(2, CROSSFIT_ADDITIONAL_EXPERT_TRAINING_ONE), user);

                getTraining(Days.WEDNESDAY, FULL_BODY, 10,
                        CROSSFIT_EXPERT_TRAINING_TWO,
                        getRandomExercises(0, CROSSFIT_ADDITIONAL_EXPERT_TRAINING_TWO), user);

                getTraining(Days.FRIDAY, FULL_BODY, 10,
                        CROSSFIT_EXPERT_TRAINING_THREE,
                        getRandomExercises(2, CROSSFIT_ADDITIONAL_EXPERT_TRAINING_THREE), user);

                getTraining(Days.FRIDAY, FULL_BODY, 10,
                        CROSSFIT_EXPERT_TRAINING_FOUR,
                        getRandomExercises(2, CROSSFIT_ADDITIONAL_EXPERT_TRAINING_FOUR), user);

                getTraining(Days.FRIDAY, FULL_BODY, 10,
                        CROSSFIT_EXPERT_TRAINING_FIVE,
                        getRandomExercises(1, CROSSFIT_ADDITIONAL_EXPERT_TRAINING_FIVE), user);
            }
            case PROFESSIONAL -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        CROSSFIT_PROFESSIONAL_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsCrossfit(user);

                getTraining(Days.MONDAY, FULL_BODY, 10,
                        CROSSFIT_PROFESSIONAL_TRAINING_ONE,
                        getRandomExercises(2, CROSSFIT_ADDITIONAL_PROFESSIONAL_TRAINING_ONE), user);

                getTraining(Days.WEDNESDAY, FULL_BODY, 10,
                        CROSSFIT_PROFESSIONAL_TRAINING_TWO,
                        getRandomExercises(0, CROSSFIT_ADDITIONAL_PROFESSIONAL_TRAINING_TWO), user);

                getTraining(Days.FRIDAY, FULL_BODY, 10,
                        CROSSFIT_PROFESSIONAL_TRAINING_THREE,
                        getRandomExercises(2, CROSSFIT_ADDITIONAL_PROFESSIONAL_TRAINING_THREE), user);

                getTraining(Days.FRIDAY, FULL_BODY, 10,
                        CROSSFIT_PROFESSIONAL_TRAINING_FOUR,
                        getRandomExercises(2, CROSSFIT_ADDITIONAL_PROFESSIONAL_TRAINING_FOUR), user);

                getTraining(Days.FRIDAY, FULL_BODY, 10,
                        CROSSFIT_PROFESSIONAL_TRAINING_FIVE,
                        getRandomExercises(1, CROSSFIT_ADDITIONAL_PROFESSIONAL_TRAINING_FIVE), user);

                getTraining(Days.FRIDAY, FULL_BODY, 10,
                        CROSSFIT_PROFESSIONAL_TRAINING_SIX,
                        getRandomExercises(1, CROSSFIT_ADDITIONAL_PROFESSIONAL_TRAINING_SIX), user);
            }
        }
    }

    @Override
    public void setWeightAndRepsCrossfit(User user) {
        List<Exercise> exercises = user.getResults().get(user.getResults().size() - 1).getExercisesResults();

        for (Exercise exercise : exercises) {
            int lastWorkingReps = exercise.getLastTrainingRepsPerSet().stream().mapToInt(Integer::intValue).sum();

            List<Integer> reps = new ArrayList<>();
            if (lastWorkingReps < 75) {
                reps.add(15);
                reps.add(15);
                reps.add(15);
                reps.add(15);
                reps.add(15);
                exercise.setNextExceptedRepsPerSet(reps);
            }
            else if (lastWorkingReps < 90) {
                reps.add(15);
                reps.add(15);
                reps.add(15);
                reps.add(15);
                reps.add(15);
                reps.add(15);
                exercise.setNextExceptedRepsPerSet(reps);
            }
            else if (lastWorkingReps < 105) {
                reps.add(15);
                reps.add(15);
                reps.add(15);
                reps.add(15);
                reps.add(15);
                reps.add(15);
                reps.add(15);
                exercise.setNextExceptedRepsPerSet(reps);
            }
            else if (lastWorkingReps < 120) {
                reps.add(15);
                reps.add(15);
                reps.add(15);
                reps.add(15);
                reps.add(15);
                reps.add(15);
                reps.add(15);
                reps.add(15);
                exercise.setNextExceptedRepsPerSet(reps);
            }
            else if (lastWorkingReps < 135) {
                reps.add(15);
                reps.add(15);
                reps.add(15);
                reps.add(15);
                reps.add(15);
                reps.add(15);
                reps.add(15);
                reps.add(15);
                reps.add(15);
                exercise.setNextExceptedRepsPerSet(reps);
            }
            else if (lastWorkingReps < 150) {
                reps.add(15);
                reps.add(15);
                reps.add(15);
                reps.add(15);
                reps.add(15);
                reps.add(15);
                reps.add(15);
                reps.add(15);
                reps.add(15);
                reps.add(15);
                exercise.setNextExceptedRepsPerSet(reps);
            }
            else {
                float currentWeight = exercise.getCurrentWorkingWeight();
                exercise.getPreviousWeights().add(currentWeight);
                exercise.setCurrentWorkingWeight(currentWeight + 2.5f);
                reps.add(15);
                reps.add(15);
                reps.add(15);
                reps.add(15);
                reps.add(15);
                exercise.setNextExceptedRepsPerSet(reps);
            }
            exerciseRepository.save(exercise);
        }

        userRepository.save(user);
    }

    @Override
    public void generateOlympicPlan(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        FitnessLevels userFitnessLevel = user.getLevel();
        List<ExercisesConstants> alreadyDoneExercises = exerciseService.getExercisesForUser(user);
        List<ExercisesConstants> neverDoneExercises;

        switch (userFitnessLevel) {
            case BEGINNER -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        OLYMPIC_BEGINNER_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsPowerliftingAndOlympic(user);

                getTraining(Days.MONDAY, FULL_BODY, 110,
                        OLYMPIC_BEGINNER_TRAINING_ONE,
                        getRandomExercises(2, OLYMPIC_ADDITIONAL_BEGINNER_TRAINING_ONE), user);

                getTraining(Days.WEDNESDAY, FULL_BODY, 110,
                        OLYMPIC_BEGINNER_TRAINING_TWO,
                        getRandomExercises(2, OLYMPIC_ADDITIONAL_BEGINNER_TRAINING_TWO), user);

                getTraining(Days.FRIDAY, FULL_BODY, 110,
                        OLYMPIC_BEGINNER_TRAINING_THREE,
                        getRandomExercises(2, OLYMPIC_ADDITIONAL_BEGINNER_TRAINING_THREE), user);
            }
            case INTERMEDIATE -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        OLYMPIC_INTERMEDIATE_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsPowerliftingAndOlympic(user);

                getTraining(Days.MONDAY, FULL_BODY, 110,
                        OLYMPIC_INTERMEDIATE_TRAINING_ONE,
                        getRandomExercises(2, OLYMPIC_ADDITIONAL_INTERMEDIATE_TRAINING_ONE), user);

                getTraining(Days.WEDNESDAY, FULL_BODY, 110,
                        OLYMPIC_INTERMEDIATE_TRAINING_TWO,
                        getRandomExercises(2, OLYMPIC_ADDITIONAL_INTERMEDIATE_TRAINING_TWO), user);

                getTraining(Days.FRIDAY, FULL_BODY, 110,
                        OLYMPIC_INTERMEDIATE_TRAINING_THREE,
                        getRandomExercises(2, OLYMPIC_ADDITIONAL_INTERMEDIATE_TRAINING_THREE), user);
            }
            case ADVANCED -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        OLYMPIC_ADVANCED_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsPowerliftingAndOlympic(user);

                getTraining(Days.MONDAY, FULL_BODY, 110,
                        OLYMPIC_ADVANCED_TRAINING_ONE,
                        getRandomExercises(2, OLYMPIC_ADDITIONAL_ADVANCED_TRAINING_ONE), user);

                getTraining(Days.WEDNESDAY, FULL_BODY, 110,
                        OLYMPIC_ADVANCED_TRAINING_TWO,
                        getRandomExercises(2, OLYMPIC_ADDITIONAL_ADVANCED_TRAINING_TWO), user);

                getTraining(Days.FRIDAY, FULL_BODY, 110,
                        OLYMPIC_ADVANCED_TRAINING_THREE,
                        getRandomExercises(2, OLYMPIC_ADDITIONAL_ADVANCED_TRAINING_THREE), user);

                getTraining(Days.FRIDAY, FULL_BODY, 110,
                        OLYMPIC_ADVANCED_TRAINING_FOUR,
                        getRandomExercises(2, OLYMPIC_ADDITIONAL_ADVANCED_TRAINING_FOUR), user);
            }
            case EXPERT -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        OLYMPIC_EXPERT_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsPowerliftingAndOlympic(user);

                getTraining(Days.MONDAY, FULL_BODY, 110,
                        OLYMPIC_EXPERT_TRAINING_ONE,
                        getRandomExercises(2, OLYMPIC_ADDITIONAL_EXPERT_TRAINING_ONE), user);

                getTraining(Days.WEDNESDAY, FULL_BODY, 110,
                        OLYMPIC_EXPERT_TRAINING_TWO,
                        getRandomExercises(2, OLYMPIC_ADDITIONAL_EXPERT_TRAINING_TWO), user);

                getTraining(Days.FRIDAY, FULL_BODY, 110,
                        OLYMPIC_EXPERT_TRAINING_THREE,
                        getRandomExercises(2, OLYMPIC_ADDITIONAL_EXPERT_TRAINING_THREE), user);

                getTraining(Days.FRIDAY, FULL_BODY, 110,
                        OLYMPIC_EXPERT_TRAINING_FOUR,
                        getRandomExercises(1, OLYMPIC_ADDITIONAL_EXPERT_TRAINING_FOUR), user);

                getTraining(Days.FRIDAY, FULL_BODY, 110,
                        OLYMPIC_EXPERT_TRAINING_FIVE,
                        getRandomExercises(1, OLYMPIC_ADDITIONAL_EXPERT_TRAINING_FIVE), user);
            }
            case PROFESSIONAL -> {
                neverDoneExercises = exerciseService.getNeverDoneExercises(alreadyDoneExercises,
                        OLYMPIC_PROFESSIONAL_EXERCISES);
                exerciseService.setExercisesInitialWeight(user, neverDoneExercises);

                setWeightAndRepsPowerliftingAndOlympic(user);

                getTraining(Days.MONDAY, FULL_BODY, 110,
                        OLYMPIC_PROFESSIONAL_TRAINING_ONE,
                        getRandomExercises(2, OLYMPIC_ADDITIONAL_PROFESSIONAL_TRAINING_ONE), user);

                getTraining(Days.WEDNESDAY, FULL_BODY, 110,
                        OLYMPIC_PROFESSIONAL_TRAINING_TWO,
                        getRandomExercises(2, OLYMPIC_ADDITIONAL_PROFESSIONAL_TRAINING_TWO), user);

                getTraining(Days.FRIDAY, FULL_BODY, 110,
                        OLYMPIC_PROFESSIONAL_TRAINING_THREE,
                        getRandomExercises(2, OLYMPIC_ADDITIONAL_PROFESSIONAL_TRAINING_THREE), user);

                getTraining(Days.FRIDAY, FULL_BODY, 110,
                        OLYMPIC_PROFESSIONAL_TRAINING_FOUR,
                        getRandomExercises(1, OLYMPIC_ADDITIONAL_PROFESSIONAL_TRAINING_FOUR), user);

                getTraining(Days.FRIDAY, FULL_BODY, 110,
                        OLYMPIC_PROFESSIONAL_TRAINING_FIVE,
                        getRandomExercises(1, OLYMPIC_ADDITIONAL_PROFESSIONAL_TRAINING_FIVE), user);

                getTraining(Days.FRIDAY, FULL_BODY, 110,
                        OLYMPIC_PROFESSIONAL_TRAINING_SIX,
                        getRandomExercises(2, OLYMPIC_ADDITIONAL_PROFESSIONAL_TRAINING_SIX), user);
            }
        }
    }

    @Override
    public void generateCardioPlan(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        FitnessLevels userFitnessLevel = user.getLevel();

        switch (userFitnessLevel) {
            case BEGINNER -> {
                getTraining(Days.MONDAY, FULL_BODY, 110,
                        CARDIO_BEGINNER_TRAINING_ONE,
                        getRandomExercises(1, CARDIO_ADDITIONAL_BEGINNER_TRAINING_ONE), user);

                getTraining(Days.WEDNESDAY, FULL_BODY, 110,
                        CARDIO_BEGINNER_TRAINING_TWO,
                        getRandomExercises(0, CARDIO_ADDITIONAL_BEGINNER_TRAINING_TWO), user);

                getTraining(Days.FRIDAY, FULL_BODY, 110,
                        CARDIO_BEGINNER_TRAINING_THREE,
                        getRandomExercises(1, CARDIO_ADDITIONAL_BEGINNER_TRAINING_THREE), user);
            }
            case INTERMEDIATE -> {
                getTraining(Days.MONDAY, FULL_BODY, 110,
                        CARDIO_INTERMEDIATE_TRAINING_ONE,
                        getRandomExercises(0, CARDIO_ADDITIONAL_INTERMEDIATE_TRAINING_ONE), user);

                getTraining(Days.WEDNESDAY, FULL_BODY, 110,
                        CARDIO_INTERMEDIATE_TRAINING_TWO,
                        getRandomExercises(0, CARDIO_ADDITIONAL_INTERMEDIATE_TRAINING_TWO), user);

                getTraining(Days.FRIDAY, FULL_BODY, 110,
                        CARDIO_INTERMEDIATE_TRAINING_THREE,
                        getRandomExercises(0, CARDIO_ADDITIONAL_INTERMEDIATE_TRAINING_THREE), user);
            }
            case ADVANCED -> {
                getTraining(Days.MONDAY, FULL_BODY, 110,
                        CARDIO_ADVANCED_TRAINING_ONE,
                        getRandomExercises(1, CARDIO_ADDITIONAL_ADVANCED_TRAINING_ONE), user);

                getTraining(Days.WEDNESDAY, FULL_BODY, 110,
                        CARDIO_ADVANCED_TRAINING_TWO,
                        getRandomExercises(1, CARDIO_ADDITIONAL_ADVANCED_TRAINING_TWO), user);

                getTraining(Days.FRIDAY, FULL_BODY, 110,
                        CARDIO_ADVANCED_TRAINING_THREE,
                        getRandomExercises(1, CARDIO_ADDITIONAL_ADVANCED_TRAINING_THREE), user);

                getTraining(Days.FRIDAY, FULL_BODY, 110,
                        CARDIO_ADVANCED_TRAINING_FOUR,
                        getRandomExercises(0, CARDIO_ADDITIONAL_ADVANCED_TRAINING_FOUR), user);
            }
            case EXPERT -> {
                getTraining(Days.MONDAY, FULL_BODY, 110,
                        CARDIO_EXPERT_TRAINING_ONE,
                        getRandomExercises(1, CARDIO_ADDITIONAL_EXPERT_TRAINING_ONE), user);

                getTraining(Days.WEDNESDAY, FULL_BODY, 110,
                        CARDIO_EXPERT_TRAINING_TWO,
                        getRandomExercises(0, CARDIO_ADDITIONAL_EXPERT_TRAINING_TWO), user);

                getTraining(Days.FRIDAY, FULL_BODY, 110,
                        CARDIO_EXPERT_TRAINING_THREE,
                        getRandomExercises(0, CARDIO_ADDITIONAL_EXPERT_TRAINING_THREE), user);

                getTraining(Days.FRIDAY, FULL_BODY, 110,
                        CARDIO_EXPERT_TRAINING_FOUR,
                        getRandomExercises(0, CARDIO_ADDITIONAL_EXPERT_TRAINING_FOUR), user);

                getTraining(Days.FRIDAY, FULL_BODY, 110,
                        CARDIO_EXPERT_TRAINING_FIVE,
                        getRandomExercises(0, CARDIO_ADDITIONAL_EXPERT_TRAINING_FIVE), user);
            }
            case PROFESSIONAL -> {
                getTraining(Days.MONDAY, FULL_BODY, 110,
                        CARDIO_PROFESSIONAL_TRAINING_ONE,
                        getRandomExercises(0, CARDIO_ADDITIONAL_PROFESSIONAL_TRAINING_ONE), user);

                getTraining(Days.WEDNESDAY, FULL_BODY, 110,
                        CARDIO_PROFESSIONAL_TRAINING_TWO,
                        getRandomExercises(0, CARDIO_ADDITIONAL_PROFESSIONAL_TRAINING_TWO), user);

                getTraining(Days.FRIDAY, FULL_BODY, 110,
                        CARDIO_PROFESSIONAL_TRAINING_THREE,
                        getRandomExercises(1, CARDIO_ADDITIONAL_PROFESSIONAL_TRAINING_THREE), user);

                getTraining(Days.FRIDAY, FULL_BODY, 110,
                        CARDIO_PROFESSIONAL_TRAINING_FOUR,
                        getRandomExercises(1, CARDIO_ADDITIONAL_PROFESSIONAL_TRAINING_FOUR), user);

                getTraining(Days.FRIDAY, FULL_BODY, 110,
                        CARDIO_PROFESSIONAL_TRAINING_FIVE,
                        getRandomExercises(1, CARDIO_ADDITIONAL_PROFESSIONAL_TRAINING_FIVE), user);

                getTraining(Days.FRIDAY, FULL_BODY, 110,
                        CARDIO_PROFESSIONAL_TRAINING_SIX,
                        getRandomExercises(0, CARDIO_ADDITIONAL_PROFESSIONAL_TRAINING_SIX), user);
            }
        }
    }

    @Override
    public Training getTraining(Days day, List<MuscleGroup> muscleGroups, int restTimeSeconds, List<ExercisesConstants> baseExercises,
                                List<ExercisesConstants> additionalExercises, User user) {
        Training training = Training.builder()
                .user(user)
                .day(day)
                .forMuscleGroup(muscleGroups)
                .restTimeSeconds(restTimeSeconds)
                .exercises(Collections.unmodifiableList(
                        Stream.concat(baseExercises.stream(), additionalExercises.stream())
                                .collect(Collectors.toList())))
                .build();
        trainingRepository.save(training);
        return training;
    }

    @Override
    public List<ExercisesConstants> getRandomExercises(int numberOfExercises,
                                                       List<ExercisesConstants> exercisesConstantsFromList) {
        List<ExercisesConstants> chosenExercises = new ArrayList<>();
        List<Integer> chosenExercisesIndexes = new ArrayList<>();

        while (chosenExercisesIndexes.size() != numberOfExercises) {
            Integer index = ThreadLocalRandom.current().nextInt(0, exercisesConstantsFromList.size() - 1);
            if (chosenExercisesIndexes.isEmpty() || !chosenExercisesIndexes.contains(index)){
                chosenExercises.add(exercisesConstantsFromList.get(index));
                chosenExercisesIndexes.add(index);
            }
        }

        return chosenExercises;
    }
}