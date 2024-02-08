package com.bkikenski.ultimatefitness.web;

import com.bkikenski.ultimatefitness.model.dto.ExerciseDTO;
import com.bkikenski.ultimatefitness.model.dto.ExerciseResultsDTO;
import com.bkikenski.ultimatefitness.service.ExerciseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/exercise")
public class ExerciseController {
    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/{exerciseId}")
    public String getExercise(@PathVariable Long exerciseId, Model model) throws IOException, InterruptedException {
        ExerciseDTO exerciseDTO = exerciseService.findExerciseById(exerciseId);
        List<Float> exerciseWorkingWeights = exerciseService.findAllExerciseWorkingWeights(exerciseId);

        model.addAttribute("exercise", exerciseDTO);
        model.addAttribute("data", exerciseWorkingWeights);
        return "exercise-page";
    }

    @PostMapping("/{exerciseId}/enter-results")
    public String enterExerciseResults(@PathVariable Long exerciseId, ExerciseResultsDTO exerciseResults) {
        exerciseService.enterResults(exerciseId, exerciseResults);
        return String.format("redirect:/exercise/%s", exerciseId);
    }
}
