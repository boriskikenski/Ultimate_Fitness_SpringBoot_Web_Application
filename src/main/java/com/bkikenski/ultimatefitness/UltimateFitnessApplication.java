package com.bkikenski.ultimatefitness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UltimateFitnessApplication {

    public static void main(String[] args) {
        SpringApplication.run(UltimateFitnessApplication.class, args);
    }

}
