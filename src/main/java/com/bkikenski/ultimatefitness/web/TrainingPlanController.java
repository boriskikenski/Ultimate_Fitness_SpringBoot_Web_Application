package com.bkikenski.ultimatefitness.web;

import com.bkikenski.ultimatefitness.model.dto.TrainingPlanPageDTO;
import com.bkikenski.ultimatefitness.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/training-plan")
public class TrainingPlanController {
    private final UserService userService;

    public TrainingPlanController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getTrainingPlanPage(@AuthenticationPrincipal UserDetails userDetails,
                                      Model model){
        String username = userDetails.getUsername();
        List<TrainingPlanPageDTO> trainingPlan = userService.getUserTrainingPlan(username);
        model.addAttribute("trainingPlan", trainingPlan);
        return "training-plan-page";
    }
}
