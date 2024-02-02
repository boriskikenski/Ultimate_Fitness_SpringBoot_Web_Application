package com.bkikenski.ultimatefitness.repository;

import com.bkikenski.ultimatefitness.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}
