package br.com.allrisk.weather.api.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class LocationModel {

    private Long id;
    private String name;
    private BigDecimal lat;
    private BigDecimal lon;
    private String country;
    private String state;
    private boolean isFavorite;

}
