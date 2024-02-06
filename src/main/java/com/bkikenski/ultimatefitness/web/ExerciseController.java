package com.bkikenski.ultimatefitness.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exercise")
public class ExerciseController {

    @GetMapping("/{exerciseId}")
    public String getExercise(@PathVariable Long exerciseId){
        System.out.println(exerciseId);
        return "";
    }
}
