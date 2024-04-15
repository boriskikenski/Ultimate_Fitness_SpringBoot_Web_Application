package com.bkikenski.ultimatefitness.repository;

import com.bkikenski.ultimatefitness.model.entity.WeeklyResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeeklyResultsRepository extends JpaRepository<WeeklyResults, Long> {
}
