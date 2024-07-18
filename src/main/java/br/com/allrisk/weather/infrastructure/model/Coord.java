package br.com.allrisk.weather.infrastructure.model;

import java.math.BigDecimal;

public record Coord(BigDecimal lon, BigDecimal lat) {
}