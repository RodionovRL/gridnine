package com.gridnine.testing.model;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FlightTest {

    @Test
    public void getSegments() {
        Segment segment1 = new Segment(LocalDateTime.now().minusDays(2).minusHours(5),
                LocalDateTime.now().minusDays(2).minusHours(2));
        Segment segment2 = new Segment(LocalDateTime.now().minusDays(3).minusHours(6),
                LocalDateTime.now().minusDays(3).minusHours(3));
        Segment segment3 = new Segment(LocalDateTime.now().minusDays(4).minusHours(7),
                LocalDateTime.now().minusDays(4).minusHours(4));

        List<Segment> expected = Arrays.asList(segment1, segment2, segment3);

        Flight flight = new Flight(Arrays.asList(segment1, segment2, segment3));

        List<Segment> actual = flight.getSegments();

        assertEquals(expected, actual);

    }


}