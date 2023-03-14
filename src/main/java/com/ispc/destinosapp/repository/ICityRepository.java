package com.ispc.destinosapp.repository;

import com.ispc.destinosapp.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICityRepository extends JpaRepository<City, Long> {


}
