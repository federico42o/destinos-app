package com.ispc.destinosapp.service;

import com.ispc.destinosapp.dto.DestinationRequestDTO;
import com.ispc.destinosapp.dto.DestinationResponseDTO;
import com.ispc.destinosapp.model.Destination;

import java.util.List;

public interface IDestinationService {
    List<DestinationResponseDTO> findAll();

    void delete(Long id);

    Destination update(Long id, DestinationRequestDTO dto);


    DestinationResponseDTO getById(Long id);

    List<DestinationResponseDTO> getByCity(String city);

}
