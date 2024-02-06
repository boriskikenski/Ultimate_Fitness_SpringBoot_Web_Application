package com.bkikenski.ultimatefitness.web;

import com.bkikenski.ultimatefitness.model.dto.TrainingDTO;
import com.bkikenski.ultimatefitness.model.exceptions.RestDayException;
import com.bkikenski.ultimatefitness.service.TrainingService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/training")
public class TrainingController {
    private final TrainingService trainingService;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
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
        return "";
    }
}
