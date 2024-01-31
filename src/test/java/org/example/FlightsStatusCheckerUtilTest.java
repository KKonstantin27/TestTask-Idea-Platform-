package org.example;

import org.example.entities.Flight;
import org.example.enums.FlightStatus;
import org.example.utils.FlightsStatusCheckerUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class FlightsStatusCheckerUtilTest {
    private static FlightsStatusCheckerUtil flightsStatusCheckerUtil;
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    @BeforeAll
    public static void initTestEnv() {
        flightsStatusCheckerUtil = new FlightsStatusCheckerUtil();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void buildFromToStringShouldReturnCorrectString() {
        Flight flight = new Flight("F11", 7, "khabarovsk", "moscow", 8, FlightStatus.UNDEFINED);
        String fromToString = flightsStatusCheckerUtil.buildFromToString(flight);
        Assertions.assertEquals("KHA_MOS", fromToString);
    }

    @Test
    public void printFlightsPossibilityShouldPrintCorrectString() {
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight("F12", 7, "khabarovsk", "moscow", 8, FlightStatus.SCHEDULED));
        flights.add(new Flight("F13", 1, "moscow", "khabarovsk", 8, FlightStatus.CANCELED));

        flightsStatusCheckerUtil.printFlightsPossibility(flights);

        String expectedPrint =
                """
                        F12 | khabarovsk -> moscow | по расписанию\r
                        F13 | moscow -> khabarovsk | отменён\r
                        """;
        Assertions.assertEquals(expectedPrint, outContent.toString());
    }

    @AfterAll
    public static void destroyTestEnv() {
        System.setOut(originalOut);
    }
}
