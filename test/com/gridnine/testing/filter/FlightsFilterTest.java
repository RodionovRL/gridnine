package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.*;

public class FlightsFilterTest {
    @Test
    public void filterTest() {
        Segment segment1 = new Segment(LocalDateTime.now().minusDays(4).minusHours(5),
                LocalDateTime.now().minusDays(4).minusHours(2));
        Segment segment2 = new Segment(LocalDateTime.now().minusDays(3).minusHours(2),
                LocalDateTime.now().minusDays(3).minusHours(3));
        Segment segment3 = new Segment(LocalDateTime.now().minusDays(2).minusHours(1),
                LocalDateTime.now().minusDays(2).plusHours(4));

        Flight flight1 = new Flight(Arrays.asList(segment1, segment2, segment3));

        Segment segment4 = new Segment(LocalDateTime.now().minusDays(5).minusHours(6),
                LocalDateTime.now().minusDays(5).minusHours(3));
        Segment segment5 = new Segment(LocalDateTime.now().minusDays(4).minusHours(3),
                LocalDateTime.now().minusDays(4).minusHours(4));
        Segment segment6 = new Segment(LocalDateTime.now().minusDays(3).minusHours(2),
                LocalDateTime.now().minusDays(3).minusHours(5));

        Flight flight2 = new Flight(Arrays.asList(segment4, segment5, segment6));

        Segment segment7 = new Segment(LocalDateTime.now().minusDays(6).minusHours(7),
                LocalDateTime.now().minusDays(6).minusHours(4));
        Segment segment8 = new Segment(LocalDateTime.now().minusDays(5).minusHours(4),
                LocalDateTime.now().minusDays(5).minusHours(5));
        Segment segment9 = new Segment(LocalDateTime.now().minusDays(4).minusHours(3),
                LocalDateTime.now().minusDays(4).minusHours(6));

        Flight flight3 = new Flight(Arrays.asList(segment7, segment8, segment9));

        List<Flight> flights = Arrays.asList(flight1, flight2, flight3);

        Predicate<Segment> isArrivalAfterLastTwoDaysSegment =
                s -> s.getArrivalDate().isAfter(LocalDateTime.now().minusDays(2));
        Predicate<Flight> isArrivalAfterLastTwoDays =
                f -> f.getSegments().stream().anyMatch(isArrivalAfterLastTwoDaysSegment);

        FlightsFilter flightsFilter = new FlightsFilter();

        List<Flight> expected = List.of(flight1);
        List<Flight> actual =  flightsFilter.filter(flights, isArrivalAfterLastTwoDays, false);

        assertEquals(expected, actual);


    }
}