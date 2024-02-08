package com.bkikenski.ultimatefitness.web;

import com.bkikenski.ultimatefitness.service.UserService;
import com.bkikenski.ultimatefitness.service.utility.UtilityService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = {"", "/", "/home"})
public class HomeController {
    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getHomePage(@AuthenticationPrincipal UserDetails userDetails,
                              Model model){
        String username = userDetails.getUsername();
        String motivationalMessage = UtilityService.getMotivationalMessage();
        List<Float> data = userService.getAllUserWeights(username);

        model.addAttribute("motivationalMessage", motivationalMessage);
        model.addAttribute("data", data);

        return "home";
    }
}
