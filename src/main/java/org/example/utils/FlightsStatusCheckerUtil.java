package org.example.utils;

import org.example.entities.Flight;

import java.util.List;

public class FlightsStatusCheckerUtil {
    public String buildFromToString(Flight flight) {
        return flight.getFrom().substring(0, 3).toUpperCase() + "_" + flight.getTo().substring(0, 3).toUpperCase();
    }

    public void printFlightsPossibility(List<Flight> flights) {
        flights.forEach(flight -> System.out.printf("%s | %s -> %s | %s%n",
                flight.getNo(),
                flight.getFrom(),
                flight.getTo(),
                flight.getFlightStatus().getTextValue()));
    }
}
