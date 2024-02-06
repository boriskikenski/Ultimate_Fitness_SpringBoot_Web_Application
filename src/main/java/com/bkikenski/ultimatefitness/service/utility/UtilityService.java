package com.bkikenski.ultimatefitness.service.utility;

import com.bkikenski.ultimatefitness.model.enumerations.Days;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class UtilityService {

    public static Days getDay(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        switch (dayOfWeek) {
            case MONDAY -> {return Days.MONDAY;}
            case TUESDAY -> {return Days.TUESDAY;}
            case WEDNESDAY -> {return Days.WEDNESDAY;}
            case THURSDAY -> {return Days.THURSDAY;}
            case FRIDAY -> {return Days.FRIDAY;}
            case SATURDAY -> {return Days.SATURDAY;}
            case SUNDAY -> {return Days.SUNDAY;}
            default -> {return null;}
        }
    }
}
