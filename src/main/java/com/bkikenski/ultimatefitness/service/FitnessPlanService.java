package com.bkikenski.ultimatefitness.service;

import com.bkikenski.ultimatefitness.model.enumerations.FitnessPlans;

import java.util.List;

public interface FitnessPlanService {
    List<FitnessPlans> getPotentialPlans(Long id);
}
