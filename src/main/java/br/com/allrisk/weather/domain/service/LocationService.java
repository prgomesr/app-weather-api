package br.com.allrisk.weather.domain.service;

import br.com.allrisk.weather.domain.model.Location;
import br.com.allrisk.weather.domain.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Transactional
    public Location add(Location location) {
        Optional<Location> exists = locationRepository.findByLatAndLon(location.getLat(), location.getLon());
        return exists.orElseGet(() -> locationRepository.save(location));
    }

    @Transactional
    public void delete(final Long id) {
        locationRepository.findById(id).ifPresent(locationRepository::delete);
    }

    @Transactional
    public void updateFavorite(final Long id) {
        locationRepository.findById(id).ifPresent(location -> {
            location.setFavorite(!location.isFavorite());
            locationRepository.save(location);
        });
    }

}
