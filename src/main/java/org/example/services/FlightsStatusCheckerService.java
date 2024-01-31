package org.example.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.CityWeather;
import org.example.entities.Flight;
import org.example.entities.Forecast;
import org.example.entities.JSONData;
import org.example.enums.FlightStatus;
import org.example.enums.TimeOffset;
import org.example.utils.FlightsStatusCheckerUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class FlightsStatusCheckerService {
    private Forecast forecast;
    private List<Flight> flights;
    private static final int WIND_LIMIT = 30;
    private static final int VISIBILITY_LIMIT = 200;
    private static String pathToJson = "/flights_and_forecast.json";

    private final FlightsStatusCheckerUtil flightsStatusCheckerUtil;

    public FlightsStatusCheckerService() {
        this.flightsStatusCheckerUtil = new FlightsStatusCheckerUtil();
    }

    public void checkFlightsPossibility() {
        readFromJSON();
        for (Flight flight : flights) {
            if (isGoodWeatherAtDeparture(flight) && isGoodWeatherAtArriving(flight)) {
                flight.setFlightStatus(FlightStatus.SCHEDULED);
            } else {
                flight.setFlightStatus(FlightStatus.CANCELED);
            }
        }
        flightsStatusCheckerUtil.printFlightsPossibility(flights);
    }

    private boolean isGoodWeatherAtDeparture(Flight flight) {
        int departureLocalTime = flight.getDeparture();
        CityWeather cityWeather = forecast.getCityWeatherAtTime(flight.getFrom(), departureLocalTime);
        return cityWeather.getWind() <= WIND_LIMIT && cityWeather.getVisibility() >= VISIBILITY_LIMIT;
    }

    private boolean isGoodWeatherAtArriving(Flight flight) {
        int arrivingLocalTime = flight.getDeparture()
                + flight.getDuration()
                + TimeOffset.valueOf(flightsStatusCheckerUtil.buildFromToString(flight)).getOffset();
        CityWeather cityWeather = forecast.getCityWeatherAtTime(flight.getTo(), arrivingLocalTime);
        return cityWeather.getWind() <= WIND_LIMIT && cityWeather.getVisibility() >= VISIBILITY_LIMIT;
    }

    private void readFromJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream is = getClass().getResourceAsStream(pathToJson);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            String currentLine;
            StringBuilder json = new StringBuilder();
            while ((currentLine = reader.readLine()) != null) {
                json.append(currentLine);
            }
            JSONData JSONData = objectMapper.readValue(json.toString(), JSONData.class);
            flights = JSONData.getFlights();
            forecast = JSONData.getForecast();
        } catch (IOException e) {
            throw new RuntimeException("File not found");
        }
    }

    public static void setPathToJson(String pathToJson) {
        FlightsStatusCheckerService.pathToJson = pathToJson;
    }
}
