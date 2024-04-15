package com.bkikenski.ultimatefitness.service.impl;

import com.bkikenski.ultimatefitness.model.dto.*;
import com.bkikenski.ultimatefitness.model.entity.*;
import com.bkikenski.ultimatefitness.model.enumerations.*;
import com.bkikenski.ultimatefitness.model.exceptions.PasswordsDoNotMatchException;
import com.bkikenski.ultimatefitness.model.exceptions.UserNotFoundException;
import com.bkikenski.ultimatefitness.model.exceptions.UsernameAlreadyExistsException;
import com.bkikenski.ultimatefitness.repository.ExerciseRepository;
import com.bkikenski.ultimatefitness.repository.UserRepository;
import com.bkikenski.ultimatefitness.repository.WeeklyResultsRepository;
import com.bkikenski.ultimatefitness.service.ExerciseService;
import com.bkikenski.ultimatefitness.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bkikenski.ultimatefitness.model.enumerations.ExercisesConstants.*;
import static com.bkikenski.ultimatefitness.model.enumerations.FitnessPlans.CROSSFIT;
import static com.bkikenski.ultimatefitness.model.enumerations.MuscleGroup.*;

@Service
public class UserServiceImplementation implements UserService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final WeeklyResultsRepository weeklyResultsRepository;
    private final ExerciseRepository exerciseRepository;
    private final ExerciseService exerciseService;

    public UserServiceImplementation(BCryptPasswordEncoder passwordEncoder, UserRepository userRepository,
                                     WeeklyResultsRepository weeklyResultsRepository, ExerciseRepository exerciseRepository, ExerciseService exerciseService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.weeklyResultsRepository = weeklyResultsRepository;
        this.exerciseRepository = exerciseRepository;
        this.exerciseService = exerciseService;
    }

    @Override
    public Long createUser(RegisterPersonalInfoDTO request) {

        if (!request.getPassword().equals(request.getRepeatPassword()))
            throw new PasswordsDoNotMatchException();
        if(this.userRepository.findByUsername(request.getUsername()).isPresent())
            throw new UsernameAlreadyExistsException(request.getUsername());

        return userRepository.save(User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .sex(request.getSex())
                .age(request.getAge())
                .height(request.getHeight())
                .startWeight(request.getWeight())
                .dailySteps(request.getSteps())
                .goal(request.getGoal())
                .role(Role.ROLE_USER)
                .build())
                .getUserId();
    }

    @Override
    public void updateUserPersonalInfo(String currentUser, UserPersonalInfoDTO userPersonalInfo) {
        User user = userRepository.findByUsername(currentUser).orElseThrow(UserNotFoundException::new);

        user.setName(userPersonalInfo.getName());
        user.setSurname(userPersonalInfo.getSurname());
        user.setAge(userPersonalInfo.getAge());
        user.setHeight(userPersonalInfo.getHeight());
        user.setDailySteps(userPersonalInfo.getDailySteps());

        userRepository.save(user);
    }

    @Override
    public void updateUserGoal(String currentUser, Goals selectedGoal) {
        User user = userRepository.findByUsername(currentUser).orElseThrow(UserNotFoundException::new);
        user.setGoal(selectedGoal);
        userRepository.save(user);
    }

    @Override
    public void updateUserFitnessPlan(String currentUser, FitnessPlans chosenPlan) {
        User user = userRepository.findByUsername(currentUser).orElseThrow(UserNotFoundException::new);
        user.setCurrentFitnessPlan(chosenPlan);
        userRepository.save(user);
    }

    @Override
    public UserDTO getUserDetails(String username) {
        User user = this.userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        return UserDTO.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .username(username)
                .sex(user.getSex())
                .age(user.getAge())
                .height(user.getHeight())
                .weight(user.getCurrentWeight())
                .goal(user.getGoal())
                .currentFitnessPlan(user.getCurrentFitnessPlan())
                .dailySteps(user.getDailySteps())
                .build();
    }

    @Override
    public UserPersonalInfoDTO getUserPersonalInfoDetails(String username) {
        User user = this.userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        return UserPersonalInfoDTO.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .height(user.getHeight())
                .dailySteps(user.getDailySteps())
                .build();
    }

    @Override
    public DietPlan getUserDietPlan(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new)
                .getDietPlan();
    }

    @Override
    public List<TrainingPlanPageDTO> getUserTrainingPlan(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        List<Training> trainingPlan = user.getTrainingPlan();
        return trainingPlan.stream()
                .map(training ->
                        TrainingPlanPageDTO.builder()
                                .trainingId(training.getId())
                                .day(training.getDay())
                                .trainingForMuscles(training.getForMuscleGroup())
                                .build())
                .toList();
    }

    @Override
    public void setFitnessPlan(RegisterChosePlanDTO request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(UserNotFoundException::new);
        user.setCurrentFitnessPlan(request.getPlan());
        userRepository.save(user);
    }

    @Override
    public void saveInitialResults(RegisterInsertResultsDTO request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(UserNotFoundException::new);
        FitnessPlans userPlan = user.getCurrentFitnessPlan();
        WeeklyResults initialResult = new WeeklyResults();
        List<WeeklyResults> weeklyResults = new ArrayList<>();
        Map<MuscleGroup, Float> initialDimensionsPerBodyPart = new HashMap<>();
        List<Exercise> initialExercises = new ArrayList<>();

        if (isValid(request.getBenchOneRepMax())) {
            Exercise e = Exercise.builder()
                    .exerciseName(userPlan == CROSSFIT ? BENCH_PRESS_CROSSFIT : BENCH_PRESS)
                    .personalRecord(request.getBenchOneRepMax())
                    .currentWorkingWeight(request.getBenchOneRepMax() * 0.8f)
                    .build();
            exerciseRepository.save(e);
            initialExercises.add(e);
        }
        if (isValid(request.getDeadLiftOneRepMax())) {
            Exercise e = Exercise.builder()
                    .exerciseName(userPlan == CROSSFIT ? DEADLIFT_CROSSFIT : DEADLIFT)
                    .personalRecord(request.getDeadLiftOneRepMax())
                    .currentWorkingWeight(request.getDeadLiftOneRepMax() * 0.8f)
                    .build();
            exerciseRepository.save(e);
            initialExercises.add(e);
        }
        if (isValid(request.getSquatOneRepMax())) {
            Exercise e = Exercise.builder()
                    .exerciseName(userPlan == CROSSFIT ? SQUATS_CROSSFIT : SQUATS)
                    .personalRecord(request.getSquatOneRepMax())
                    .currentWorkingWeight(request.getSquatOneRepMax() * 0.8f)
                    .build();
            exerciseRepository.save(e);
            initialExercises.add(e);
        }
        if (isValid(request.getOverHeadPressOneRepMax())) {
            Exercise e = Exercise.builder()
                    .exerciseName(userPlan == CROSSFIT ? OVERHEAD_PRESS_CROSSFIT : OVERHEAD_PRESS)
                    .personalRecord(request.getOverHeadPressOneRepMax())
                    .currentWorkingWeight(request.getOverHeadPressOneRepMax() * 0.8f)
                    .build();
            exerciseRepository.save(e);
            initialExercises.add(e);
        }
        if (isValid(request.getCleanAndJerkMax())) {
            Exercise e = Exercise.builder()
                    .exerciseName(userPlan == CROSSFIT ? CLEAN_AND_JERK_CROSSFIT : CLEAN_AND_JERK)
                    .personalRecord(request.getCleanAndJerkMax())
                    .currentWorkingWeight(request.getCleanAndJerkMax() * 0.8f)
                    .build();
            exerciseRepository.save(e);
            initialExercises.add(e);
        }
        if (isValid(request.getLongestRun())) {
            Exercise e = Exercise.builder()
                    .exerciseName(RUN)
                    .personalRecord(request.getLongestRun())
                    .build();
            exerciseRepository.save(e);
            initialExercises.add(e);
        }
        if (isValid(request.getBestRunTimeFiveK())) {
            Exercise e = Exercise.builder()
                    .exerciseName(RUN_5K)
                    .personalRecord(request.getBestRunTimeFiveK())
                    .build();
            exerciseRepository.save(e);
            initialExercises.add(e);
        }
        if (isValid(request.getBestRunTimeTenK())) {
            Exercise e = Exercise.builder()
                    .exerciseName(RUN_10K)
                    .personalRecord(request.getBestRunTimeTenK())
                    .build();
            exerciseRepository.save(e);
            initialExercises.add(e);
        }
        if (isValid(request.getChestSize())) {
            initialDimensionsPerBodyPart.put(CHEST, request.getChestSize());
        }
        if (isValid(request.getWaistSize())) {
            initialDimensionsPerBodyPart.put(MuscleGroup.ABS, request.getWaistSize());
        }
        if (isValid(request.getBicepsSize())) {
            initialDimensionsPerBodyPart.put(BICEPS, request.getBicepsSize());
        }
        if (isValid(request.getQuadsSize())) {
            initialDimensionsPerBodyPart.put(LEGS, request.getQuadsSize());
        }

        initialResult.setExercisesResults(initialExercises);
        initialResult.setDimensionsPerBodyPart(initialDimensionsPerBodyPart);
        initialResult.setUser(user);
        initialResult.setBodyWeight(user.getStartWeight());
        weeklyResultsRepository.save(initialResult);
        weeklyResults.add(initialResult);
        user.setResults(weeklyResults);
        userRepository.save(user);
    }

    private boolean isValid(Float field) {
        return field != null && !field.isNaN();
    }

    @Override
    public void setUserLevel(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Sex userSex = user.getSex();

        if (user.getResults().isEmpty()){
            user.setLevel(FitnessLevels.BEGINNER);
        } else {
            WeeklyResults userLastWeeklyResults = user.getResults().get(user.getResults().size() - 1);
            float userBodyWeight = userLastWeeklyResults.getBodyWeight();

            int exerciseSumLevel = userLastWeeklyResults.getExercisesResults().stream()
                    .mapToInt(exercise -> {
                        if (user.getCurrentFitnessPlan() != FitnessPlans.CARDIO)
                            return this.exerciseService.getExerciseLevel(
                                    exercise.getExerciseName(), userSex,
                                    exercise.getCurrentWorkingWeight() / userBodyWeight);
                        else
                            return this.exerciseService.getExerciseLevel(
                                    exercise.getExerciseName(), userSex,
                                    exercise.getPersonalRecord());
                    })
                    .sum();
            float userLevel = (float) exerciseSumLevel / userLastWeeklyResults.getExercisesResults().size();

            if (userLevel < 1)
                user.setLevel(FitnessLevels.BEGINNER);
            else if (userLevel < 2)
                user.setLevel(FitnessLevels.INTERMEDIATE);
            else if (userLevel < 3)
                user.setLevel(FitnessLevels.ADVANCED);
            else if (userLevel < 4)
                user.setLevel(FitnessLevels.EXPERT);
            else if (userLevel < 5)
                user.setLevel(FitnessLevels.PROFESSIONAL);
        }

        userRepository.save(user);
    }

    @Override
    public void insertBodyResults(String username, InsertBodyResultsDTO results) {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        WeeklyResults weeklyResults = user.getResults().get(user.getResults().size() - 1);
        Map<MuscleGroup, Float> dimensionsPerBodyPart = new HashMap<>();

        dimensionsPerBodyPart.put(CHEST, results.getChestSize());
        dimensionsPerBodyPart.put(MuscleGroup.ABS, results.getWaistSize());
        dimensionsPerBodyPart.put(BICEPS, results.getBicepsSize());
        dimensionsPerBodyPart.put(LEGS, results.getQuadsSize());

        weeklyResults.setDimensionsPerBodyPart(dimensionsPerBodyPart);
        weeklyResults.setBodyWeight(results.getWeight());

        weeklyResultsRepository.save(weeklyResults);
        userRepository.save(user);
    }

    @Override
    public List<Float> getAllUserWeights(String username) {
        List<Float> weights = new ArrayList<>();
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        List<WeeklyResults> userResults = user.getResults();
        weights.add(user.getStartWeight());
        userResults.forEach(weeklyResults -> weights.add(weeklyResults.getBodyWeight()));
        return weights;
    }
}
