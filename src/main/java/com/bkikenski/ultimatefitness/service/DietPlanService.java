package com.bkikenski.ultimatefitness.service;

import com.bkikenski.ultimatefitness.model.User;
import com.bkikenski.ultimatefitness.model.enumerations.FitnessLevels;

public interface DietPlanService {
    void generateDietPlan(Long userId);
    double calculateBMR(User user);
    int calculateTDEE(double bmr, FitnessLevels userLevel);
}
