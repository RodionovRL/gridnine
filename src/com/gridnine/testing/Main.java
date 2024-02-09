package com.gridnine.testing;

import com.gridnine.testing.filter.FlightsFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        List<Flight> testFlights = new ArrayList<>(FlightBuilder.createFlights());
        FlightsFilter flightsFilter = new FlightsFilter();

        System.out.println(testFlights);

        Predicate<Segment> isArrivalBeforeNowSegment = s -> s.getDepartureDate().isBefore(LocalDateTime.now());
        Predicate<Flight> isArrivalBeforeNow = f -> f.getSegments().stream().anyMatch(isArrivalBeforeNowSegment);
        System.out.println((flightsFilter.filter(testFlights, isArrivalBeforeNow, true)));

        Predicate<Segment> isArrivalBeforeDepartureSegment = s -> s.getArrivalDate().isBefore(s.getDepartureDate());
        Predicate<Flight> isArrivalBeforeDeparture = f -> f.getSegments().stream()
                .anyMatch(isArrivalBeforeDepartureSegment);

        System.out.println((flightsFilter.filter(testFlights, isArrivalBeforeDeparture, true)));

        Predicate<Flight> isLandTimeLongerTwoHours = f -> {
            for (int i = 0; i < f.getSegments().size() - 1; i++) {
                if (f.getSegments().get(i).getArrivalDate().plusHours(2)
                        .isAfter(f.getSegments().get(i + 1).getDepartureDate())) {
                    return true;
                }
            }
            return false;
        };

        System.out.println(flightsFilter.filter(testFlights, isLandTimeLongerTwoHours, true));
    }
}