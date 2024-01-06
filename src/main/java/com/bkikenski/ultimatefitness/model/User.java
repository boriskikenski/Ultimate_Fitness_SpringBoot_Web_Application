package com.bkikenski.ultimatefitness.model;

import com.bkikenski.ultimatefitness.model.enumerations.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;

    private String surname;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private Sex sex;

    private int age;

    private int height;

    private float startWeight;

    @Enumerated(value = EnumType.STRING)
    private FitnessLevels level;

    @Enumerated(value = EnumType.STRING)
    private Goals goal;

    @OneToMany
    private List<Results> results;

    @Enumerated(value = EnumType.STRING)
    private FitnessPlans currentFitnessPlan;

    @ManyToMany()
    private List<Training> trainingPlan; //TODO relacijata

    @OneToOne
    private DietPlan dietPlan;

    private int dailySteps;
}
