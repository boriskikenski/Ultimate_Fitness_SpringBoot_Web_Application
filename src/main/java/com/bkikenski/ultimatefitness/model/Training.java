package com.bkikenski.ultimatefitness.model;

import com.bkikenski.ultimatefitness.model.enumerations.Days;
import com.bkikenski.ultimatefitness.model.enumerations.Exercises;
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

    @ElementCollection
    @Enumerated(value = EnumType.STRING)
    private List<MuscleGroup> forMuscleGroup;

    @Enumerated(value = EnumType.STRING)
    private Days day;

    private int restTimeSeconds;

    @ElementCollection
    private List<Exercises> exercises;
}
