package br.com.allrisk.weather.infrastructure.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
public class GeoLocation {

    private String name;
    private BigDecimal lat;
    private BigDecimal lon;
    private String country;
    private String state;


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        GeoLocation geoLocation = (GeoLocation) object;
        return Objects.equals(name, geoLocation.name) && Objects.equals(country, geoLocation.country) && Objects.equals(state, geoLocation.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, state);
    }

}
