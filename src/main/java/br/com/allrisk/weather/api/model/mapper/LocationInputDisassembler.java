package br.com.allrisk.weather.api.model.mapper;

import br.com.allrisk.weather.api.model.LocationInput;
import br.com.allrisk.weather.domain.model.Location;
import org.springframework.stereotype.Component;

@Component
public class LocationInputDisassembler {

    public Location toDomainObject(LocationInput input) {
        Location location = new Location();

        location.setName(input.getName());
        location.setLat(input.getLat());
        location.setLon(input.getLon());
        location.setCountry(input.getCountry());
        location.setState(input.getState());
        location.setFavorite(false);

        return location;
    }

}
