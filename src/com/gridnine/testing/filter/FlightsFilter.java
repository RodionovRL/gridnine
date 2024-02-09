package com.gridnine.testing.filter;


import com.gridnine.testing.model.Flight;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FlightsFilter {
    public List<Flight> filter(List<Flight> flights, Predicate<Flight> flightPredicate, boolean isReverse) {
        List<Flight> filtered = flights.stream()
                .filter(flightPredicate)
                .collect(Collectors.toList());
        if (isReverse) {
            flights.removeAll(filtered);
            return flights;
        }
        return filtered;
    }

}
