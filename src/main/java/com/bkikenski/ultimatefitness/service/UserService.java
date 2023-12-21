package com.bkikenski.ultimatefitness.service;

import com.bkikenski.ultimatefitness.model.dto.RegisterChosePlanDTO;
import com.bkikenski.ultimatefitness.model.dto.RegisterInsertResultsDTO;
import com.bkikenski.ultimatefitness.model.dto.RegisterPersonalInfoDTO;

public interface UserService {
    Long createUser(RegisterPersonalInfoDTO request);
    void setFitnessPlan(RegisterChosePlanDTO request);
    void saveInitialResults(RegisterInsertResultsDTO request);
}
