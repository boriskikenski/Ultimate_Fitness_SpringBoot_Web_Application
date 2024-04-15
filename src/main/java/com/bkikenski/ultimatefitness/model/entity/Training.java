package com.bkikenski.ultimatefitness.model.entity;

import com.bkikenski.ultimatefitness.model.enumerations.Days;
import com.bkikenski.ultimatefitness.model.enumerations.ExercisesConstants;
import com.bkikenski.ultimatefitness.model.enumerations.MuscleGroup;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ElementCollection
    @Enumerated(value = EnumType.STRING)
    private List<MuscleGroup> forMuscleGroup;

    @Enumerated(value = EnumType.STRING)
    private Days day;

    private int restTimeSeconds;

    @ElementCollection
    @Enumerated(value = EnumType.STRING)
    private List<ExercisesConstants> exercises;
}
