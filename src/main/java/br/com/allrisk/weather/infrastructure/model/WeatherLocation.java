package br.com.allrisk.weather.infrastructure.model;

import java.util.ArrayList;

public record WeatherLocation(Coord coord, ArrayList<Weather> weather, String base, Main main,
                              Integer visibility, Wind wind, Clouds clouds, Integer dt, Sys sys, Integer timezone,
                              Integer id, String name, Integer cod) {
}
