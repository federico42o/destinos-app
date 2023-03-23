package com.ispc.destinosapp.service;

import com.ispc.destinosapp.model.City;

import java.util.List;

public interface ICityService {
    List<City> findAll();

    City findById(Long id);
}
