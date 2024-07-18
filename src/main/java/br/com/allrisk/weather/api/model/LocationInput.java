package br.com.allrisk.weather.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class LocationInput {

    @NotBlank
    private String name;

    @NotNull
    private BigDecimal lat;

    @NotNull
    private BigDecimal lon;

    @NotBlank
    private String country;

    @NotBlank
    private String state;

}
