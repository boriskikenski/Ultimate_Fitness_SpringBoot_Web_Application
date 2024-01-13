package com.bkikenski.ultimatefitness.service.impl;

import com.bkikenski.ultimatefitness.model.dto.RapidAPIResponseDTO;
import com.bkikenski.ultimatefitness.service.RapidAPIService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RapidAPIServiceImplementation implements RapidAPIService {

    public static final String X_RAPID_API_KEY = "X-RapidAPI-Key";
    public static final String X_RAPID_API_VALUE = "d7c004f037msha216c3f0cf7186ep102c39jsncfe70d0c3cfb";
    public static final String X_RAPID_API_HOST = "X-RapidAPI-Host";
    public static final String X_RAPID_API_HOST_VALUE = "exercisedb.p.rapidapi.com";

    @Override
    public RapidAPIResponseDTO call(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header(X_RAPID_API_KEY, X_RAPID_API_VALUE)
                .header(X_RAPID_API_HOST, X_RAPID_API_HOST_VALUE)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> rapidAPIResponse = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return extractData(Objects.requireNonNull(parseJsonString(rapidAPIResponse.body())));
    }

    private RapidAPIResponseDTO extractData(JsonNode responseBody) {
        return RapidAPIResponseDTO.builder()
                .equipment(responseBody.get("equipment").asText())
                .gifExplanationUrl(responseBody.get("gifUrl").asText())
                .instructions(extractList(responseBody.get("instructions")))
                .build();
    }

    private List<String> extractList(JsonNode jsonArray){
        List<String> elementList = new ArrayList<>();
        for (JsonNode e : jsonArray) {
            elementList.add(e.asText());
        }
        return elementList;
    }

    private static JsonNode parseJsonString(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readTree(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
