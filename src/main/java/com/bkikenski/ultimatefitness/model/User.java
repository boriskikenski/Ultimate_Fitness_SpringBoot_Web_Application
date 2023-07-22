package com.bkikenski.ultimatefitness.model;

import com.bkikenski.ultimatefitness.model.enumerations.Goals;
import com.bkikenski.ultimatefitness.model.enumerations.Sex;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;

    private String surname;

    @Enumerated(value = EnumType.STRING)
    private Sex sex;

    private int age;

    private int level;

    @Enumerated(value = EnumType.STRING)
    private Goals goal;

    @OneToMany
    private List<Results> results;

    @OneToOne
    private TrainingPlan trainingPlan;

    @OneToOne
    private DietPlan dietPlan;

    @Nullable
    private int dailySteps;
}
