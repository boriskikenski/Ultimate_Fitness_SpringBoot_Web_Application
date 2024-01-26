package com.bkikenski.ultimatefitness.web;

import com.bkikenski.ultimatefitness.model.dto.UserDTO;
import com.bkikenski.ultimatefitness.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfilePageController {
    private final UserService userService;

    public ProfilePageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getProfilePage(@AuthenticationPrincipal UserDetails userDetails,
                                 Model model) {
        String currentUser = userDetails.getUsername();
        UserDTO userDTO = userService.getUserDetails(currentUser);
        model.addAttribute("user", userDTO);
        return "profile-page";
    }

    @GetMapping("/update-personal-info")
    public String getUpdatePersonalInfoPage(@AuthenticationPrincipal UserDetails userDetails,
                                            Model model) {
        return "";
    }

    @GetMapping("/update-goal")
    public String getUpdateGoalPage(@AuthenticationPrincipal UserDetails userDetails,
                                    Model model) {
        return "";
    }

    @GetMapping("/update-training-plan")
    public String getUpdateTrainingPlanPage(@AuthenticationPrincipal UserDetails userDetails,
                                            Model model) {
        return "";
    }
}
