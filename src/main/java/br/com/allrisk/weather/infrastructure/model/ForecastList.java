package br.com.allrisk.weather.infrastructure.model;

import java.util.ArrayList;

public record ForecastList(Integer dt, Main main, ArrayList<Weather> weather, Clouds clouds, Wind wind, Integer visibility, Double pop,
                           Rain rain, Sys sys, String dt_txt) {
}