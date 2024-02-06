package com.bkikenski.ultimatefitness.web;

import com.bkikenski.ultimatefitness.service.utility.UtilityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"", "/", "/home"})
public class HomeController {

    @GetMapping
    public String getHomePage(Model model){
        String motivationalMessage = UtilityService.getMotivationalMessage();
        model.addAttribute("motivationalMessage", motivationalMessage);
        return "home";
    }
}
