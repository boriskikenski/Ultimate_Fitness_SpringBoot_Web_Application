package com.bkikenski.ultimatefitness.service;

import com.bkikenski.ultimatefitness.model.dto.RapidAPIResponseDTO;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface RapidAPIService {
    RapidAPIResponseDTO call(String url) throws IOException, InterruptedException;
}
