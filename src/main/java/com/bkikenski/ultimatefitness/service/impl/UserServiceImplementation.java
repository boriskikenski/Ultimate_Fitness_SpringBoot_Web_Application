package com.bkikenski.ultimatefitness.service.impl;

import com.bkikenski.ultimatefitness.model.Results;
import com.bkikenski.ultimatefitness.model.User;
import com.bkikenski.ultimatefitness.model.dto.RegisterChosePlanDTO;
import com.bkikenski.ultimatefitness.model.dto.RegisterInsertResultsDTO;
import com.bkikenski.ultimatefitness.model.dto.RegisterPersonalInfoDTO;
import com.bkikenski.ultimatefitness.model.enumerations.FitnessLevels;
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
                .role(Role.ROLE_COSTUMER)
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
        setUserLevel(request.getUserId()); //ToDo change location of this call
    }

    @Override
    public void setUserLevel(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Sex userSex = user.getSex();

        if (user.getResults().isEmpty()){
            user.setLevel(FitnessLevels.BEGINNER);
        } else {
            Results userLastResults = user.getResults().get(user.getResults().size() - 1);
            float userBodyWeight = userLastResults.getWeight();

            int exerciseSumLevel =userLastResults.getExercisesResults().stream()
                    .mapToInt(exercise ->
                            this.exerciseService.getExerciseLevel(
                                    exercise.getExerciseName(), userSex,
                                    exercise.getCurrentWorkingWeight() / userBodyWeight))
                    .sum();
            float userLevel = (float) exerciseSumLevel / userLastResults.getExercisesResults().size();

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
