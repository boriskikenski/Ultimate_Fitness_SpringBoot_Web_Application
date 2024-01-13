package com.bkikenski.ultimatefitness.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RapidAPIResponseDTO {
    private String equipment;
    private String gifExplanationUrl;
    private List<String> instructions;
}
/*

{
        "bodyPart": "",
        "equipment": "",
        "gifUrl": "",
        "id": "",
        "name": "",
        "target": "",
        "secondaryMuscles": [],
        "instructions": []
}

*/
