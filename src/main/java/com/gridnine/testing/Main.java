package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        FilterRuleImpl filterRule = new FilterRuleImpl();
        List<Flight>testList = FlightBuilder.createFlights();

        testList.forEach(System.out::println);
        System.out.println("_________________");

        List<Flight> transfer = filterRule.filterByTransfer(1, testList);
        List<Flight>beforeDeparture = filterRule.filterArrivingBeforeDeparture(testList);
        List<Flight>beforeNow = filterRule.filterDepartureBeforeNow(testList);
        List<Flight>moreThanTwoHours = filterRule.filterByMoreThanTwoHoursOnEarth(testList);

    }
}
