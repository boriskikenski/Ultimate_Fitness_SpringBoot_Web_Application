package com.bkikenski.ultimatefitness.service.utility;

import com.bkikenski.ultimatefitness.model.enumerations.Days;
import com.bkikenski.ultimatefitness.model.enumerations.MotivationalMessages;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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

    private static List<String> getMotivationMessages() {
        MotivationalMessages[] values = MotivationalMessages.values();
        String[] messages = new String[values.length];

        for (int i = 0; i < values.length; i++) {
            messages[i] = values[i].getMessage();
        }

        return Arrays.asList(messages);
    }

    public static String getMotivationalMessage() {
        List<String> allMessages = getMotivationMessages();
        int index = ThreadLocalRandom.current().nextInt(0, allMessages.size() - 1);
        return allMessages.get(index);
    }
}
