package br.com.allrisk.weather.domain.repository;

import br.com.allrisk.weather.domain.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    Optional<Location> findByLatAndLon(final BigDecimal lat, final BigDecimal lon);

}
