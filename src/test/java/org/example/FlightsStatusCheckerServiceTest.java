package org.example;

import org.example.services.FlightsStatusCheckerService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class FlightsStatusCheckerServiceTest {
    private static FlightsStatusCheckerService flightsStatusCheckerService;
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    @BeforeAll
    public static void initTestEnv() {
        FlightsStatusCheckerService.setPathToJson("src/test/resources/flights_and_forecast.json");
        flightsStatusCheckerService = new FlightsStatusCheckerService();
        System.setOut(new PrintStream(outContent));
    }

    /**
     * JSON для данного теста сформирован так, чтобы проверить следующие случаи:
     * F1 - Хорошая погода как в месте отправки, так и в месте прибытия
     * F2 - Хорошая погода в месте отправки, сильный ветер в месте прибытия
     * F1 - Хорошая погода в месте отправки, плохая видимость в месте прибытия
     * F1 - Сильный ветер в месте отправки, хорошая погода в месте прибытия
     * F1 - Плохая видимость в месте отправки, хорошая погода в месте прибытия
     */

    @Test
    public void checkFlightsPossibilityShouldPrintCorrectStringWithCorrectStatuses() {
        flightsStatusCheckerService.checkFlightsPossibility();

        String expectedPrint =
                """
                        F1 | novosibirsk -> omsk | по расписанию\r
                        F2 | moscow -> novosibirsk | отменён\r
                        F3 | moscow -> omsk | отменён\r
                        F4 | omsk -> moscow | отменён\r
                        F5 | moscow -> novosibirsk | отменён\r
                        """;
        Assertions.assertEquals(expectedPrint, outContent.toString());
    }

    @AfterAll
    public static void destroyTestEnv() {
        System.setOut(originalOut);
    }
}
