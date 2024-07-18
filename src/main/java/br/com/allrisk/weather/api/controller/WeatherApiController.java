package br.com.allrisk.weather.api.controller;

import br.com.allrisk.weather.infrastructure.client.OpenWeatherClient;
import br.com.allrisk.weather.infrastructure.model.Forecast;
import br.com.allrisk.weather.infrastructure.model.GeoLocation;
import br.com.allrisk.weather.infrastructure.model.WeatherLocation;
import br.com.allrisk.weather.infrastructure.service.OpenWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("v1/weathers")
public class WeatherApiController {

    private final OpenWeatherService openWeatherService;
    private final OpenWeatherClient openWeatherClient;

    @Autowired
    public WeatherApiController(OpenWeatherService openWeatherService, OpenWeatherClient openWeatherClient) {
        this.openWeatherService = openWeatherService;
        this.openWeatherClient = openWeatherClient;
    }

    @GetMapping("locations")
    public List<GeoLocation> findAllLocationsByName(@RequestParam String name) {
        return openWeatherService.getLocationByName(name);
    }

    @GetMapping
    public WeatherLocation getWeatherByLatAndLon(@RequestParam BigDecimal lat, @RequestParam BigDecimal lon) {
        return openWeatherClient.getWeatherByLatAndLon(lat, lon);
    }

    @GetMapping("forecast")
    public Forecast getForecastByLatAndLon(@RequestParam BigDecimal lat, @RequestParam BigDecimal lon) {
        return openWeatherService.getForecastByLatAndLon(lat, lon);
    }

}
