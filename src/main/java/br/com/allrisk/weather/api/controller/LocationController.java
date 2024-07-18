package br.com.allrisk.weather.api.controller;

import br.com.allrisk.weather.api.model.LocationInput;
import br.com.allrisk.weather.api.model.LocationModel;
import br.com.allrisk.weather.api.model.mapper.LocationInputDisassembler;
import br.com.allrisk.weather.api.model.mapper.LocationModelAssembler;
import br.com.allrisk.weather.domain.model.Location;
import br.com.allrisk.weather.domain.repository.LocationRepository;
import br.com.allrisk.weather.domain.service.LocationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("v1/locations")
public class LocationController {

    private final LocationModelAssembler modelAssembler;
    private final LocationInputDisassembler inputDisassembler;
    private final LocationService locationService;
    private final LocationRepository locationRepository;

    @Autowired
    public LocationController(LocationModelAssembler modelAssembler,
                              LocationInputDisassembler inputDisassembler,
                              LocationService locationService, LocationRepository locationRepository) {
        this.modelAssembler = modelAssembler;
        this.inputDisassembler = inputDisassembler;
        this.locationService = locationService;
        this.locationRepository = locationRepository;
    }

    @GetMapping
    public List<LocationModel> findAll() {
        List<Location> locations = locationRepository.findAll();

        List<LocationModel> models = modelAssembler.toCollectionModel(locations);
        models.sort(Comparator.comparing(LocationModel::isFavorite).reversed());

        return models;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LocationModel add(@RequestBody @Valid LocationInput input) {
        Location location = inputDisassembler.toDomainObject(input);
        location = locationService.add(location);
        return modelAssembler.toModel(location);
    }

    @PutMapping("{id}")
    public void updateFavorite(@PathVariable Long id) {
        locationService.updateFavorite(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        locationService.delete(id);
    }

}
