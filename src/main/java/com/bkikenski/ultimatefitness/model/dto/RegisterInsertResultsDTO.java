package com.bkikenski.ultimatefitness.model.dto;

import lombok.Data;

@Data
public class RegisterInsertResultsDTO {
    private Long userId;
    private float benchOneRepMax;
    private float deadLiftOneRepMax;
    private float squatOneRepMax;
    private float overHeadPressOneRepMax;
    private float cleanAndJerkMax;
    private float longestRun;
    private float bestRunTimeFiveK;
    private float bestRunTimeTenK;
    private float averageTrainingTime;
    private float chestSize;
    private float waistSize;
    private float bicepsSize;
    private float quadsSize;
}
