package br.com.allrisk.weather.infrastructure.model;

public record Weather(Integer id, String main, String description, String icon) {
}