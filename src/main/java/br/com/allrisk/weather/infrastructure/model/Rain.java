package br.com.allrisk.weather.infrastructure.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Rain(@JsonProperty("3h") Double _3h) {
}