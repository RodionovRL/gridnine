package com.gridnine.testing.model;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class SegmentTest {

    @Test
    public void getDepartureDate() {
        LocalDateTime actualDeparture = LocalDateTime.now().minusDays(2).minusHours(5);
        LocalDateTime actualArrive = LocalDateTime.now().minusDays(2).minusHours(2);
        Segment segment = new Segment(actualDeparture, actualArrive);

        assertEquals(actualDeparture, segment.getDepartureDate());
    }

    @Test
    public void getArrivalDate() {
        LocalDateTime actualDeparture = LocalDateTime.now().minusDays(2).minusHours(5);
        LocalDateTime actualArrive = LocalDateTime.now().minusDays(2).minusHours(2);
        Segment segment = new Segment(actualDeparture, actualArrive);

        assertEquals(actualArrive, segment.getArrivalDate());
    }
}