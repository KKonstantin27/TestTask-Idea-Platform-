package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Forecast {
    private List<CityWeather> moscow;
    private List<CityWeather> novosibirsk;
    private List<CityWeather> omsk;
}
