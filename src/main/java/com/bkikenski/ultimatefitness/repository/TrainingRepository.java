package com.bkikenski.ultimatefitness.repository;

import com.bkikenski.ultimatefitness.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
}
