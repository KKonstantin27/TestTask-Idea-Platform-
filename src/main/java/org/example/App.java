package org.example;

import org.example.services.FlightsStatusCheckerService;

public class App {
    public static void main(String[] args) {
        FlightsStatusCheckerService flightsStatusCheckerService = new FlightsStatusCheckerService();
        flightsStatusCheckerService.checkFlightsPossibility();
    }
}
