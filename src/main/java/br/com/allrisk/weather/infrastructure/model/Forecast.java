package br.com.allrisk.weather.infrastructure.model;

import java.util.ArrayList;

public record Forecast(String cod, Integer message, Integer cnt, ArrayList<ForecastList> list, City city) {
}