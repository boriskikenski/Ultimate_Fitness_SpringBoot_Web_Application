package com.bkikenski.ultimatefitness.service.impl;

import com.bkikenski.ultimatefitness.model.entity.DietPlan;
import com.bkikenski.ultimatefitness.model.entity.User;
import com.bkikenski.ultimatefitness.model.enumerations.FitnessLevels;
import com.bkikenski.ultimatefitness.model.enumerations.Goals;
import com.bkikenski.ultimatefitness.model.enumerations.Sex;
import com.bkikenski.ultimatefitness.model.exceptions.UserNotFoundException;
import com.bkikenski.ultimatefitness.repository.DietPlanRepository;
import com.bkikenski.ultimatefitness.repository.UserRepository;
import com.bkikenski.ultimatefitness.service.DietPlanService;
import org.springframework.stereotype.Service;

@Service
public class DietPlanServiceImplementation implements DietPlanService {
    private final UserRepository userRepository;
    private final DietPlanRepository dietPlanRepository;

    public DietPlanServiceImplementation(UserRepository userRepository, DietPlanRepository dietPlanRepository) {
        this.userRepository = userRepository;
        this.dietPlanRepository = dietPlanRepository;
    }

    @Override
    public void generateDietPlan(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        DietPlan userDietPlan;
        if (user.getDietPlan() != null)
            userDietPlan = user.getDietPlan();
        else
            userDietPlan = new DietPlan();

        double userBMR = calculateBMR(user);
        int userTDEE = calculateTDEE(userBMR, user.getLevel());
        Goals userGoal = user.getGoal();

        switch (userGoal) {
            case LOSE_WEIGHT -> {
                userDietPlan.setMinCalories(userTDEE - 650);
                userDietPlan.setMaxCalories(userTDEE - 450);
            }
            case GAIN_WEIGHT -> {
                userDietPlan.setMinCalories(userTDEE + 650);
                userDietPlan.setMaxCalories(userTDEE + 450);
            }
            case FITNESS -> {
                userDietPlan.setMinCalories(userTDEE - 150);
                userDietPlan.setMaxCalories(userTDEE + 250);
            }
            case POWER -> {
                userDietPlan.setMinCalories(userTDEE);
                userDietPlan.setMaxCalories(userTDEE + 350);
            }
            case STAMINA -> {
                userDietPlan.setMinCalories(userTDEE);
                userDietPlan.setMaxCalories(userTDEE + 200);
            }
            case HEALTH_AND_RECREATION -> {
                userDietPlan.setMinCalories(userTDEE - 100);
                userDietPlan.setMaxCalories(userTDEE + 100);
            }
        }
        dietPlanRepository.save(userDietPlan);

        user.setDietPlan(userDietPlan);
        userRepository.save(user);
    }

    @Override
    public double calculateBMR(User user) {
        Sex userSex = user.getSex();
        int age = user.getAge();
        int height = user.getHeight();
        float weight = user.getCurrentWeight();

        switch (userSex) {
            case MALE -> {
                return 88.362 + (13.197 * weight) + (4.799 * height) - (5.677 * age);
            }
            case FEMALE -> {
                return  447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
            }
            default -> { return 0; }
        }
    }

    @Override
    public int calculateTDEE(double bmr, FitnessLevels userLevel) {
        switch (userLevel){
            case BEGINNER -> {
                return (int)(bmr * 1.2);
            }
            case INTERMEDIATE -> {
                return (int)(bmr * 1.375);
            }
            case ADVANCED -> {
                return (int)(bmr * 1.55);
            }
            case EXPERT -> {
                return (int)(bmr * 1.725);
            }
            case PROFESSIONAL -> {
                return (int)(bmr * 1.9);
            }
            default -> {return 0;}
        }
    }
}
