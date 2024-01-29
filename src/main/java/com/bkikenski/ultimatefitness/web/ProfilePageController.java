package com.bkikenski.ultimatefitness.web;

import com.bkikenski.ultimatefitness.model.dto.UserDTO;
import com.bkikenski.ultimatefitness.model.dto.UserPersonalInfoDTO;
import com.bkikenski.ultimatefitness.model.enumerations.FitnessPlans;
import com.bkikenski.ultimatefitness.model.enumerations.Goals;
import com.bkikenski.ultimatefitness.service.FitnessPlanService;
import com.bkikenski.ultimatefitness.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/profile")
public class ProfilePageController {
    private final UserService userService;
    private final FitnessPlanService fitnessPlanService;

    public ProfilePageController(UserService userService, FitnessPlanService fitnessPlanService) {
        this.userService = userService;
        this.fitnessPlanService = fitnessPlanService;
    }

    @GetMapping
    public String getProfilePage(@AuthenticationPrincipal UserDetails userDetails,
                                 Model model) {
        String currentUser = userDetails.getUsername();
        UserDTO userDTO = userService.getUserDetails(currentUser);
        model.addAttribute("user", userDTO);
        return "profile-page";
    }

    @GetMapping("/update-personal-info-page")
    public String getUpdatePersonalInfoPage(@AuthenticationPrincipal UserDetails userDetails,
                                            Model model) {
        String currentUser = userDetails.getUsername();
        UserPersonalInfoDTO userPersonalInfo = userService.getUserPersonalInfoDetails(currentUser);
        model.addAttribute("user", userPersonalInfo);
        return "update-personal-info";
    }

    @GetMapping("/update-goal-page")
    public String getUpdateGoalPage(Model model) {
        model.addAttribute("selectedFirst", Arrays.stream(Goals.values()).findFirst().orElseThrow());
        model.addAttribute("goals", Arrays.stream(Goals.values()).skip(1));
        return "update-goal";
    }

    @GetMapping("/update-training-plan-page")
    public String getUpdateTrainingPlanPage(@AuthenticationPrincipal UserDetails userDetails,
                                            Model model) {
        String currentUser = userDetails.getUsername();
        List<FitnessPlans> potentialFitnessPlans = fitnessPlanService.getPotentialPlans(currentUser);
        model.addAttribute("selectedPlan", potentialFitnessPlans.get(0));
        model.addAttribute("potentialPlans", potentialFitnessPlans.stream()
                .skip(1).collect(Collectors.toList()));
        return "update-training-plan";
    }

    @PostMapping("/update-personal-info")
    public String updatePersonalIndo(@AuthenticationPrincipal UserDetails userDetails,
                                     UserPersonalInfoDTO userPersonalInfo) {
        String currentUser = userDetails.getUsername();
        userService.updateUserPersonalInfo(currentUser, userPersonalInfo);
        return "redirect:/profile";
    }

    @PostMapping("/update-goal")
    public String updateGoal(@AuthenticationPrincipal UserDetails userDetails,
                             @RequestParam Goals goal){
        String currentUser = userDetails.getUsername();
        userService.updateUserGoal(currentUser, goal);
        return "redirect:/profile/update-training-plan-page";
    }

    @PostMapping("/update-training-plan")
    public String updateTrainingPlan(@AuthenticationPrincipal UserDetails userDetails,
                                     @RequestParam FitnessPlans plan) {
        String currentUser = userDetails.getUsername();
        userService.updateUserFitnessPlan(currentUser, plan);
        return "redirect:/profile";
    }
}
