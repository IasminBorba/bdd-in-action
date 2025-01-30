package com.bddinaction.chapter2.timetables;

import com.bddinaction.chapter2.model.Line;

import java.time.LocalTime;
import java.util.List;

public interface CanScheduleServices {
    void scheduleService(Line line, List<LocalTime> departingAt, String departure, String destination);
}

