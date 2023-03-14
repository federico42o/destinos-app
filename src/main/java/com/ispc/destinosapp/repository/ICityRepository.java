package com.ispc.destinosapp.repository;

import com.ispc.destinosapp.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICityRepository extends JpaRepository<City, Long> {

    City findCityByName(String name);

    List<City> findCitiesByName(String name);
}
