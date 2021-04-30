package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilterRuleImpl implements FilterRule {

    public List<Flight> filterByTransfer(int transferCount, List<Flight> list) {
        return list.stream()
                .filter(flight -> flight.getSegments().size() == transferCount + 1)
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> filterDepartureBeforeNow(List<Flight> list) {
        return list.stream().filter(flight -> flight.getSegments().stream()
                .allMatch(segment -> segment.getDepartureDate().isAfter(LocalDateTime.now()))).
                collect(Collectors.toList());
    }

    public List<Flight> filterArrivingBeforeDeparture(List<Flight> list) {
        return list.stream().filter(flight -> flight.getSegments().stream()
                .anyMatch(segment -> segment.getArrivalDate().isAfter(segment.getDepartureDate())))
                .collect(Collectors.toList());
    }

    //Хотел написать с помощью стримов, но никак не получалось.
    public List<Flight> filterByMoreThanTwoHoursOnEarth(List<Flight> list) {
        List<Flight> flightList = new ArrayList<>();
        for (Flight flight : list) {
            if (flight.getSegments().size() > 1) {
                int sum = 0;
                for (int i = 1; i < flight.getSegments().size(); i++) {
                    sum += hoursBetweenDepartureAndArrival(flight.getSegments().get(i - 1).getArrivalDate(),
                            flight.getSegments().get(i).getDepartureDate());
                }
                if (sum <= 2) {
                    flightList.add(flight);
                }
            } else {
                flightList.add(flight);
            }
        }
        return flightList;
    }

    public int hoursBetweenDepartureAndArrival(LocalDateTime arrival, LocalDateTime departure) {
        return (int) ChronoUnit.HOURS.between(arrival, departure);
    }

}
