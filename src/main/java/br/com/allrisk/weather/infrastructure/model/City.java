package br.com.allrisk.weather.infrastructure.model;

public record City(Integer id, String name, Coord coord, String country, Integer population, Integer timezone,
                   Integer sunrise, Integer sunset) {
}