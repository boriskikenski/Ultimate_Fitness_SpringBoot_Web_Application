package com.bkikenski.ultimatefitness.service;

import com.bkikenski.ultimatefitness.model.DietPlan;
import com.bkikenski.ultimatefitness.model.dto.*;
import com.bkikenski.ultimatefitness.model.enumerations.FitnessPlans;
import com.bkikenski.ultimatefitness.model.enumerations.Goals;

import java.util.List;

public interface UserService {
    Long createUser(RegisterPersonalInfoDTO request);
    void updateUserPersonalInfo(String currentUser, UserPersonalInfoDTO userPersonalInfo);
    void updateUserGoal(String currentUser, Goals selectedGoal);
    void updateUserFitnessPlan(String currentUser, FitnessPlans chosenPlan);
    UserDTO getUserDetails(String username);
    UserPersonalInfoDTO getUserPersonalInfoDetails(String username);
    DietPlan getUserDietPlan(String username);
    List<TrainingPlanPageDTO> getUserTrainingPlan(String username);
    void setFitnessPlan(RegisterChosePlanDTO request);
    void saveInitialResults(RegisterInsertResultsDTO request);
    void setUserLevel(Long userId);
}
