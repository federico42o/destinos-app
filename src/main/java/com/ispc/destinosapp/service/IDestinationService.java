package com.ispc.destinosapp.service;

import com.ispc.destinosapp.dto.DestinationDto;

import java.util.List;

public interface IDestinationService {
    List<DestinationDto> findAll();
}
