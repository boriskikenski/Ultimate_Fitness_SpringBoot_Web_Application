package com.bkikenski.ultimatefitness.model.dto;

import lombok.Data;

@Data
public class RegisterInsertResultsDTO {
    private Long userId;
    private Float benchOneRepMax;
    private Float deadLiftOneRepMax;
    private Float squatOneRepMax;
    private Float overHeadPressOneRepMax;
    private Float cleanAndJerkMax;
    private Float longestRun;
    private Float bestRunTimeFiveK;
    private Float bestRunTimeTenK;
    private Float averageTrainingTime;
    private Float chestSize;
    private Float waistSize;
    private Float bicepsSize;
    private Float quadsSize;
}
