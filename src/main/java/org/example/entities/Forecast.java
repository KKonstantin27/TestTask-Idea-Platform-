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

    public CityWeather getCityWeatherAtTime(String city, int time) {
        CityWeather cityWeather = new CityWeather();

        switch (city) {
            case "moscow":
                cityWeather = getMoscow().get(time);
                break;
            case "novosibirsk":
                cityWeather = getNovosibirsk().get(time);
                break;
            case "omsk":
                cityWeather = getOmsk().get(time);
                break;
        }
        return cityWeather;
    }
}
