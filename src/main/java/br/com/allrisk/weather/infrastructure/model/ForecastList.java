package br.com.allrisk.weather.infrastructure.model;

import java.time.Instant;
import java.util.ArrayList;

public record ForecastList(Instant dt, Main main, ArrayList<Weather> weather, Clouds clouds, Wind wind, Integer visibility, Double pop,
                           Rain rain, Sys sys, String dt_txt) {
}