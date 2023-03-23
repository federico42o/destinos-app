package com.ispc.destinosapp.service;

import com.ispc.destinosapp.exception.NotFoundException;
import com.ispc.destinosapp.model.City;
import com.ispc.destinosapp.repository.ICityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements ICityService {

    private final ICityRepository repository;

    @Override
    @Transactional
    public List<City> findAll() {
        return repository.findAll();
    }

    @Override
    public City findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("City not found"));
    }
}
