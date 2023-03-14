package com.ispc.destinosapp.service;

import com.ispc.destinosapp.dto.CityDTO;
import com.ispc.destinosapp.dto.DestinationDTO;
import com.ispc.destinosapp.dto.DestinationResponseDTO;
import com.ispc.destinosapp.model.City;
import com.ispc.destinosapp.model.Destination;
import com.ispc.destinosapp.repository.ICityRepository;
import com.ispc.destinosapp.repository.IDestinationDao;
import com.ispc.destinosapp.wrapper.CityWrapper;
import com.ispc.destinosapp.wrapper.DestinationWrapper;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class DestinationServiceImpl implements IDestinationService {

    private final IDestinationDao repository;
    private final ICityRepository cityRepository;

    public DestinationServiceImpl(IDestinationDao repository, ICityRepository cityRepository) {
        this.repository = repository;
        this.cityRepository = cityRepository;
    }


    @Override
    public List<DestinationResponseDTO> findAll() {
        List<Destination> list = repository.findAll();
        List<DestinationResponseDTO> destinationResponseDTOList = new ArrayList<>();
        City city = null;
        Long id = null;
        for (Destination destination : list) {
            if (destination.getLocation() != null) {
                id = destination.getLocation().getId();
                city = cityRepository.findById(id).orElse(null);
            }
            CityDTO cityDTO = CityWrapper.toDto(city);
            destinationResponseDTOList.add(DestinationResponseDTO.builder()
                    .city(CityWrapper.summaryDto(city))
                    .description(destination.getDescription())
                    .image(destination.getImage())
                    .name(destination.getName())


                    .build());
        }
        return destinationResponseDTOList;
    }

    @Override
    public void delete(Long id) {
        Destination dest = repository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("destination with id:" + id + " doesn't exist")
                );
        repository.deleteById(id);
    }

    @Override
    public DestinationDTO update(DestinationDTO dto) {
        return null;
    }

    @Override
    public DestinationDTO getById(Long id) {
        Destination dest = repository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("destination with id:" + id + " doesn't exist")
                );
        DestinationDTO dto = DestinationWrapper.toDto(dest);
        if (dest.getLocation() != null) {
            dto.setCityId(dest.getLocation().getId());
        }
        return dto;

    }

    @Override
    public List<DestinationResponseDTO> getByCity(String city) {
        List<Destination> list = repository.findAllByLocation_NameIs(city);
        City c = cityRepository.findCityByName(city);
        List<DestinationResponseDTO> response = new ArrayList<>();
        for (Destination destination : list) {
            response.add(DestinationResponseDTO.builder()
                    .city(CityWrapper.summaryDto(c))
                    .image(destination.getImage())
                    .name(destination.getName())
                    .description(destination.getName())

                    .build());
        }
        return response;
    }


    public Long create(DestinationDTO dto) {
        City city = cityRepository.findById(dto.getCityId()).orElseThrow(() -> new NotFoundException("City not found"));
        if (city != null) {
            Destination destination = Destination.builder()
                    .description(dto.getDescription())
                    .name(dto.getName())
                    .image(dto.getImage())
                    .location(city)
                    .build();
            Destination savedDestination = repository.save(destination);
            return savedDestination.getId();
        } else {
            return null;
        }

    }


}
