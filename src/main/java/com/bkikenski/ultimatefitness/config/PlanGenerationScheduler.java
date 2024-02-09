package com.bkikenski.ultimatefitness.config;

import com.bkikenski.ultimatefitness.model.User;
import com.bkikenski.ultimatefitness.repository.UserRepository;
import com.bkikenski.ultimatefitness.service.DietPlanService;
import com.bkikenski.ultimatefitness.service.TrainingPlanService;
import com.bkikenski.ultimatefitness.service.UserService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlanGenerationScheduler {
    private final UserRepository userRepository;
    private final UserService userService;
    private final TrainingPlanService trainingPlanService;
    private final DietPlanService dietPlanService;

    public PlanGenerationScheduler(UserRepository userRepository, UserService userService,
                                   TrainingPlanService trainingPlanService, DietPlanService dietPlanService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.trainingPlanService = trainingPlanService;
        this.dietPlanService = dietPlanService;
    }

    @Scheduled(cron = "0 0 0 * * SUN")
    public void generatePlan() {
        List<User> allUsers = userRepository.findAll();
        allUsers.forEach(user -> {
            userService.setUserLevel(user.getUserId());
            trainingPlanService.generateTrainingPlan(user.getUserId());
            dietPlanService.generateDietPlan(user.getUserId());
        });
    }
}
