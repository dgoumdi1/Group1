package com.example.productivitybuddy.repository;

import com.example.productivitybuddy.model.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PlanetRepository extends JpaRepository<Planet, Long> {

    Optional<Planet> findByName(String name);

    List<Planet> findByUnlockHoursLessThanEqual(Integer hours);
}
