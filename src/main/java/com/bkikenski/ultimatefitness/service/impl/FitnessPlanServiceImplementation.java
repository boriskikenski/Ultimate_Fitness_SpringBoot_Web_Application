package com.bkikenski.ultimatefitness.service.impl;

import com.bkikenski.ultimatefitness.model.enumerations.FitnessPlans;
import com.bkikenski.ultimatefitness.model.enumerations.Goals;
import com.bkikenski.ultimatefitness.model.exceptions.UserNotFoundException;
import com.bkikenski.ultimatefitness.repository.UserRepository;
import com.bkikenski.ultimatefitness.service.FitnessPlanService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FitnessPlanServiceImplementation implements FitnessPlanService {

    private final UserRepository userRepository;

    public FitnessPlanServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<FitnessPlans> getPotentialPlans(Long id) {
        Goals goal = userRepository.findById(id).orElseThrow(UserNotFoundException::new).getGoal();
        List<FitnessPlans> potentialPlans = new ArrayList<>();

        switch (goal) {
            case LOSE_WEIGHT -> potentialPlans.addAll(List.of(
                    FitnessPlans.CROSSFIT, FitnessPlans.CARDIO, FitnessPlans.BASIC_5_DAYS
            ));
            case GAIN_WEIGHT -> potentialPlans.addAll(List.of(
                    FitnessPlans.BODYBUILDING, FitnessPlans.POWERLIFTING, FitnessPlans.BASIC_3_DAYS, FitnessPlans.BASIC_4_DAYS
            ));
            case FITNESS -> potentialPlans.addAll(List.of(
                    FitnessPlans.BODYBUILDING, FitnessPlans.OLYMPIC, FitnessPlans.BASIC_4_DAYS, FitnessPlans.BASIC_5_DAYS
            ));
            case POWER -> potentialPlans.addAll(List.of(
                    FitnessPlans.BODYBUILDING, FitnessPlans.POWERLIFTING, FitnessPlans.OLYMPIC
            ));
            case STAMINA -> potentialPlans.addAll(List.of(
                    FitnessPlans.CROSSFIT, FitnessPlans.OLYMPIC, FitnessPlans.CARDIO
            ));
            case HEALTH_AND_RECREATION -> potentialPlans.addAll(List.of(
                    FitnessPlans.CARDIO, FitnessPlans.BASIC_3_DAYS, FitnessPlans.BASIC_4_DAYS, FitnessPlans.BASIC_5_DAYS
            ));
        }

        return potentialPlans;
    }
}
