package com.bkikenski.ultimatefitness.repository;

import com.bkikenski.ultimatefitness.model.DietPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DietPlanRepository extends JpaRepository<DietPlan, Long> {
}
