package com.bkikenski.ultimatefitness.web;

import com.bkikenski.ultimatefitness.model.DietPlan;
import com.bkikenski.ultimatefitness.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/diet-plan")
public class DietPlanController {
    private final UserService userService;

    public DietPlanController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getDietPlanPage(@AuthenticationPrincipal UserDetails userDetails,
                                  Model model) {
        String username = userDetails.getUsername();
        DietPlan dietPlan = userService.getUserDietPlan(username);
        model.addAttribute("maxCaloriesIntake", dietPlan.getMaxCalories());
        model.addAttribute("minCaloriesIntake", dietPlan.getMinCalories());
        return "diet-plan-page";
    }
}
