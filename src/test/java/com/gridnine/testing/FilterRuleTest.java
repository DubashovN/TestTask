package com.gridnine.testing;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterRuleTest {
    private FilterRuleImpl filterRule = new FilterRuleImpl();
    private List<Flight> list = FlightBuilder.createFlights();

    @Test
    public void filterByTransferTest() {
        LocalDateTime[] dates = {LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(4)};
        List<Segment> segments = new ArrayList<>(dates.length / 2);
        for (int i = 0; i < (dates.length - 1); i += 2) {
            segments.add(new Segment(dates[i], dates[i + 1]));
        }
        Flight flight = new Flight(segments);
        List<Flight> flightList = filterRule.filterByTransfer(0, Arrays.asList(flight));
        Assertions.assertEquals(1, flightList.size());
    }

    @Test
    public void filterDepartureBeforeNowTest(){
        List<Flight>testList = filterRule.filterDepartureBeforeNow(list);
        Assertions.assertEquals(5, testList.size());
    }

    @Test
    public void filterArrivingBeforeDepartureTest(){
        List<Flight>testList = filterRule.filterArrivingBeforeDeparture(list);
        Assertions.assertEquals(5, testList.size());
    }

    @Test
    public void filterByMoreThanTwoHoursOnEarthTest(){
        List<Flight>testList = filterRule.filterByMoreThanTwoHoursOnEarth(list);
        Assertions.assertEquals(4, testList.size());
    }
}
