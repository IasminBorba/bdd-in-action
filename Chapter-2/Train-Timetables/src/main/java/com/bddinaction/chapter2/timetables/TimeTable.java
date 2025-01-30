package com.bddinaction.chapter2.timetables;

import com.bddinaction.chapter2.model.Line;

import java.time.LocalTime;
import java.util.List;

public interface TimeTable {
    List<Line> findLinesThrough(String departingFrom, String goingTo);
    List<LocalTime> getDepartures(Line line, String departingFrom);
}
