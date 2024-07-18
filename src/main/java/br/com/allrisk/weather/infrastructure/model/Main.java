package br.com.allrisk.weather.infrastructure.model;

public record Main(Double temp, Double feels_like, Double temp_min, Double temp_max, Integer pressure, Integer humidity,
                   Integer sea_level, Integer grnd_level) {
}