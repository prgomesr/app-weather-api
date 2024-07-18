package br.com.allrisk.weather.infrastructure.client;

import br.com.allrisk.weather.core.AppConfig;
import br.com.allrisk.weather.core.CryptoUtils;
import br.com.allrisk.weather.infrastructure.model.Forecast;
import br.com.allrisk.weather.infrastructure.model.GeoLocation;
import br.com.allrisk.weather.infrastructure.model.WeatherLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@Component
public class OpenWeatherClient {
    public static final String API_URL = "https://api.openweathermap.org/";
    public static final String TEMPERATURE_IN_CELSIUS = "metric";
    public static final String LANGUAGE_PT_BR = "pt_br";

    private final RestClient restClient;
    private final AppConfig appConfig;

    @Autowired
    public OpenWeatherClient(AppConfig appConfig) {
        this.appConfig = appConfig;
        this.restClient = RestClient.builder()
                .baseUrl(API_URL)
                .build();
    }

    public List<GeoLocation> getCityByName(final String cityName) {
        URI uri = UriComponentsBuilder.fromUriString(API_URL + "geo/1.0/direct")
                .queryParam("q", cityName)
                .queryParam("appid", getAppKey())
                .queryParam("limit", 10)
                .encode()
                .build()
                .toUri();

        return restClient.get()
                .uri(uri)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    public WeatherLocation getWeatherByLatAndLon(final BigDecimal lat, final BigDecimal lon) {
        URI uri = UriComponentsBuilder.fromUriString(API_URL + "data/2.5/weather")
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("units", TEMPERATURE_IN_CELSIUS)
                .queryParam("lang", LANGUAGE_PT_BR)
                .queryParam("appid", getAppKey())
                .encode()
                .build()
                .toUri();

        return restClient.get()
                .uri(uri)
                .retrieve()
                .body(WeatherLocation.class);
    }

    public Forecast getForecastByLatAndLon(final BigDecimal lat, final BigDecimal lon) {
        URI uri = UriComponentsBuilder.fromUriString(API_URL + "data/2.5/forecast")
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("units", TEMPERATURE_IN_CELSIUS)
                .queryParam("lang", LANGUAGE_PT_BR)
                .queryParam("appid", getAppKey())
                .encode()
                .build()
                .toUri();

        return restClient.get()
                .uri(uri)
                .retrieve()
                .body(Forecast.class);
    }

    private String getAppKey() {
        try {
            return CryptoUtils.decrypt(appConfig.getApiKey(),
                    appConfig.getDecryptKey());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
