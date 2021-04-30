package com.gridnine.testing;

import com.gridnine.testing.Flight;

import java.time.LocalDateTime;
import java.util.List;

public interface FilterRule {
    List<Flight> filterByTransfer(int transferCount, List<Flight> list);
    List<Flight> filterDepartureBeforeNow(List<Flight> list);
    List<Flight> filterByMoreThanTwoHoursOnEarth(List<Flight> list);
    List<Flight> filterArrivingBeforeDeparture(List<Flight> list);
    int hoursBetweenDepartureAndArrival(LocalDateTime arrival, LocalDateTime departure);
}
