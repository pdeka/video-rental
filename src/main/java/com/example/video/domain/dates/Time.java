package com.example.video.domain.dates;

public interface Time {
    int hourOfDay();

    int minuteOfHour();

    int secondOfMinute();

    boolean isAfterNow();

    boolean isBeforeNow();
}
