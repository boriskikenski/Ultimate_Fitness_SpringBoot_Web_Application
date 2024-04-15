package com.bkikenski.ultimatefitness.web;

import com.bkikenski.ultimatefitness.model.dto.RegisterChosePlanDTO;
import com.bkikenski.ultimatefitness.model.dto.RegisterInsertResultsDTO;
import com.bkikenski.ultimatefitness.model.dto.RegisterPersonalInfoDTO;
import com.bkikenski.ultimatefitness.model.enumerations.Goals;
import com.bkikenski.ultimatefitness.service.DietPlanService;
import com.bkikenski.ultimatefitness.service.FitnessPlanService;
import com.bkikenski.ultimatefitness.service.TrainingPlanService;
import com.bkikenski.ultimatefitness.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final FitnessPlanService fitnessPlanService;
    private final TrainingPlanService trainingPlanService;
    private final DietPlanService dietPlanService;

    public AuthController(UserService userService, FitnessPlanService fitnessPlanService,
                          TrainingPlanService trainingPlanService, DietPlanService dietPlanService) {
        this.userService = userService;
        this.fitnessPlanService = fitnessPlanService;
        this.trainingPlanService = trainingPlanService;
        this.dietPlanService = dietPlanService;
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login-page";
    }

    @GetMapping("/register-personal-info")
    public String getRegisterFirstPage(Model model){
        model.addAttribute("selectedFirst", Arrays.stream(Goals.values()).findFirst().get());
        model.addAttribute("goals", Arrays.stream(Goals.values()).skip(1));
        return "register-personal-info";
    }

    @GetMapping("/register-choose-plan")
    public String getRegisterSecondPage(@RequestParam Long userId, Model model){
        model.addAttribute("userId", userId);
        model.addAttribute("selectedPlan", fitnessPlanService.getPotentialPlans(userId).get(0));
        model.addAttribute("potentialPlans",
                fitnessPlanService.getPotentialPlans(userId).stream().skip(1).collect(Collectors.toList()));
        return "register-choose-plan";
    }

    @GetMapping("/register-insert-results")
    public String getRegisterThirdPage(@RequestParam Long userId, Model model){
        model.addAttribute("userId", userId);
        return "register-insert-results";
    }

    @PostMapping("/register-personal-info")
    public ModelAndView registerStepOne(RegisterPersonalInfoDTO request,
                                        RedirectAttributes redirectAttributes){
        Long userId = userService.createUser(request);
        redirectAttributes.addAttribute("userId", userId);
        return new ModelAndView("redirect:/auth/register-choose-plan");
    }

    @PostMapping("/register-choose-plan")
    public ModelAndView registerStepTwo(RegisterChosePlanDTO request,
                                        RedirectAttributes redirectAttributes){
        if (request.getAction().equalsIgnoreCase("continue")){
            userService.setFitnessPlan(request);
            redirectAttributes.addAttribute("userId", request.getUserId());
            return new ModelAndView("redirect:/auth/register-insert-results");
        } else {
            userService.setFitnessPlan(request);
            userService.setUserLevel(request.getUserId());
            trainingPlanService.generateTrainingPlan(request.getUserId());
            dietPlanService.generateDietPlan(request.getUserId());
            return new ModelAndView("redirect:/login");
        }
    }

    @PostMapping("/register-insert-results")
    public String registerStepThree(RegisterInsertResultsDTO request){
        userService.saveInitialResults(request);
        userService.setUserLevel(request.getUserId());
        trainingPlanService.generateTrainingPlan(request.getUserId());
        dietPlanService.generateDietPlan(request.getUserId());
        return "redirect:/login";
    }

}
