package com.ispc.destinosapp.service;

import com.ispc.destinosapp.dto.DestinationDTO;
import com.ispc.destinosapp.dto.DestinationResponseDTO;

import java.util.List;

public interface IDestinationService {
    List<DestinationResponseDTO> findAll();

    void delete(Long id);

    DestinationDTO update(DestinationDTO dto);

    DestinationDTO getById(Long id);

    List<DestinationResponseDTO> getByCity(String city);

}
