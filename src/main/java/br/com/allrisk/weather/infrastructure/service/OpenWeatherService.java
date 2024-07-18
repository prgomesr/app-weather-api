package br.com.allrisk.weather.infrastructure.service;

import br.com.allrisk.weather.infrastructure.client.OpenWeatherClient;
import br.com.allrisk.weather.infrastructure.model.Forecast;
import br.com.allrisk.weather.infrastructure.model.ForecastList;
import br.com.allrisk.weather.infrastructure.model.GeoLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class OpenWeatherService {

    private final OpenWeatherClient client;

    @Autowired
    public OpenWeatherService(OpenWeatherClient client) {
        this.client = client;
    }

    public List<GeoLocation> getLocationByName(final String name) {
        List<GeoLocation> cities = client.getCityByName(name);

        return filterDuplicateCities(cities);
    }

    public Forecast getForecastByLatAndLon(final BigDecimal lat, final BigDecimal lon) {
        Forecast forecast = client.getForecastByLatAndLon(lat, lon);

        Set<LocalDate> seenDates = new LinkedHashSet<>();

        List<ForecastList> filteredForecastsByDate = getFilteredForecastsByDate(forecast.list(), seenDates);

        return new Forecast(
                forecast.cod(),
                forecast.message(),
                filteredForecastsByDate.size(),
                new ArrayList<>(filteredForecastsByDate),
                forecast.city()
        );
    }

    private List<ForecastList> getFilteredForecastsByDate(List<ForecastList> futureForecasts, Set<LocalDate> seenDates) {
        return futureForecasts.stream()
                .filter(li -> seenDates.add(getDate(li.dt())))
                .toList();
    }

    private List<GeoLocation> filterDuplicateCities(List<GeoLocation> cities) {
        Set<GeoLocation> uniqueCities = new LinkedHashSet<>(cities);

        return List.copyOf(uniqueCities);
    }

    private LocalDate getDate(Integer dt) {
        return Instant.ofEpochSecond(dt).atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
