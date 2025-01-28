package com.bddinaction.chapter2.timetables;

import java.time.LocalTime;
import java.util.List;

public interface TimeTable {
    List<String> findLinesThrough(String departingFrom, String goingTo);
    List<LocalTime> getDepartures(String lineName, String departingFrom);
    int getTravelDuration(String departingFrom, String goingTo);
}
