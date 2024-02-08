package com.bkikenski.ultimatefitness.web;

import com.bkikenski.ultimatefitness.model.dto.InsertBodyResultsDTO;
import com.bkikenski.ultimatefitness.model.dto.TrainingDTO;
import com.bkikenski.ultimatefitness.model.exceptions.RestDayException;
import com.bkikenski.ultimatefitness.service.TrainingService;
import com.bkikenski.ultimatefitness.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/training")
public class TrainingController {
    private final TrainingService trainingService;
    private final UserService userService;

    public TrainingController(TrainingService trainingService, UserService userService) {
        this.trainingService = trainingService;
        this.userService = userService;
    }

    @GetMapping
    public String getTodayTraining(@AuthenticationPrincipal UserDetails userDetails,
                                   Model model){
        String username = userDetails.getUsername();
        try{
            TrainingDTO training = trainingService.getTodayTraining(username);
            model.addAttribute("training", training);
            return "training-page";
        }catch (RestDayException exception){
            model.addAttribute("message", exception.getMessage());
            return "rest-day-page";
        }
    }

    @GetMapping("/{trainingId}")
    public String getTraining(@PathVariable Long trainingId, Model model) {
        TrainingDTO training = trainingService.findById(trainingId);
        model.addAttribute("training", training);
        return "training-page";
    }

    @PostMapping("/insert-body-results")
    public String insertBodyResults(@AuthenticationPrincipal UserDetails userDetails,
                                    InsertBodyResultsDTO bodyResults) {
        String username = userDetails.getUsername();
        userService.insertBodyResults(username, bodyResults);
        return "redirect:/training";
    }
}
