package com.bkikenski.ultimatefitness.service;

import com.bkikenski.ultimatefitness.model.User;
import com.bkikenski.ultimatefitness.model.dto.RegisterChosePlanDTO;
import com.bkikenski.ultimatefitness.model.dto.RegisterInsertResultsDTO;
import com.bkikenski.ultimatefitness.model.dto.RegisterPersonalInfoDTO;
import com.bkikenski.ultimatefitness.model.dto.UserDTO;

public interface UserService {
    Long createUser(RegisterPersonalInfoDTO request);
    UserDTO getUserDetails(String username);
    void setFitnessPlan(RegisterChosePlanDTO request);
    void saveInitialResults(RegisterInsertResultsDTO request);
    void setUserLevel(Long userId);
}
