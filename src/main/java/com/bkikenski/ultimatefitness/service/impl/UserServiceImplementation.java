package com.bkikenski.ultimatefitness.service.impl;

import com.bkikenski.ultimatefitness.model.User;
import com.bkikenski.ultimatefitness.model.dto.RegisterChosePlanDTO;
import com.bkikenski.ultimatefitness.model.dto.RegisterInsertResultsDTO;
import com.bkikenski.ultimatefitness.model.dto.RegisterPersonalInfoDTO;
import com.bkikenski.ultimatefitness.model.enumerations.Role;
import com.bkikenski.ultimatefitness.model.exceptions.PasswordsDoNotMatchException;
import com.bkikenski.ultimatefitness.model.exceptions.UserNotFoundException;
import com.bkikenski.ultimatefitness.model.exceptions.UsernameAlreadyExistsException;
import com.bkikenski.ultimatefitness.repository.UserRepository;
import com.bkikenski.ultimatefitness.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserServiceImplementation(BCryptPasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
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
    }
}
