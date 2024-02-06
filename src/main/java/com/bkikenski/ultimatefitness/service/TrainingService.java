package com.bkikenski.ultimatefitness.service;

import com.bkikenski.ultimatefitness.model.dto.TrainingDTO;

public interface TrainingService {
    TrainingDTO findById(Long trainingId);
    TrainingDTO getTodayTraining(String username);
}
