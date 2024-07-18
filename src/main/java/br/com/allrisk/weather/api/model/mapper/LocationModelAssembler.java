package br.com.allrisk.weather.api.model.mapper;

import br.com.allrisk.weather.api.model.LocationModel;
import br.com.allrisk.weather.domain.model.Location;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LocationModelAssembler {

    public LocationModel toModel(Location entity) {
        return map(entity);
    }

    public List<LocationModel> toCollectionModel(Collection<Location> entities) {
        return entities.stream().map(this::toModel).collect(Collectors.toList());
    }

    private LocationModel map(Location entity) {
        return LocationModel.builder()
                .id(entity.getId())
                .name(entity.getName())
                .lat(entity.getLat())
                .lon(entity.getLon())
                .state(entity.getState())
                .country(entity.getCountry())
                .isFavorite(entity.isFavorite())
                .build();
    }

}
