package com.bkikenski.ultimatefitness.model;

import com.bkikenski.ultimatefitness.model.enumerations.FitnessPlans;
import com.bkikenski.ultimatefitness.model.enumerations.Goals;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class FitnessPlan {
    @Id
    @Enumerated(value = EnumType.STRING)
    private FitnessPlans fitnessPlanName;

    private String description;

    @ElementCollection
    @Enumerated(value = EnumType.STRING)
    private List<Goals> fitnessPlanForGoal;

    @ManyToMany()
    private List<Training> trainings;
}
