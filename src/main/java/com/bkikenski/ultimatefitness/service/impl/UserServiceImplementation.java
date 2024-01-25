package com.bkikenski.ultimatefitness.service.impl;

import com.bkikenski.ultimatefitness.model.WeeklyResults;
import com.bkikenski.ultimatefitness.model.User;
import com.bkikenski.ultimatefitness.model.dto.RegisterChosePlanDTO;
import com.bkikenski.ultimatefitness.model.dto.RegisterInsertResultsDTO;
import com.bkikenski.ultimatefitness.model.dto.RegisterPersonalInfoDTO;
import com.bkikenski.ultimatefitness.model.enumerations.FitnessLevels;
import com.bkikenski.ultimatefitness.model.enumerations.FitnessPlans;
import com.bkikenski.ultimatefitness.model.enumerations.Role;
import com.bkikenski.ultimatefitness.model.enumerations.Sex;
import com.bkikenski.ultimatefitness.model.exceptions.PasswordsDoNotMatchException;
import com.bkikenski.ultimatefitness.model.exceptions.UserNotFoundException;
import com.bkikenski.ultimatefitness.model.exceptions.UsernameAlreadyExistsException;
import com.bkikenski.ultimatefitness.repository.UserRepository;
import com.bkikenski.ultimatefitness.service.ExerciseService;
import com.bkikenski.ultimatefitness.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ExerciseService exerciseService;

    public UserServiceImplementation(BCryptPasswordEncoder passwordEncoder, UserRepository userRepository,
                                     ExerciseService exerciseService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
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
    public void setFitnessPlan(RegisterChosePlanDTO request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(UserNotFoundException::new);
        user.setCurrentFitnessPlan(request.getPlan());
        userRepository.save(user);
    }

    @Override
    public void saveInitialResults(RegisterInsertResultsDTO request) {
        //TODO implement after fully defined use and creation of Exercise entity
        // !!!!!!!! save only non-null and not-empty
        //TODO vnimavaj na zavhuvucanje sekogash na tezhinata vo rezultatite, pri kreiranje na rezultati stavi default kilazha
        setUserLevel(request.getUserId()); //ToDo change location of this call
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
}
