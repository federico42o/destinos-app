package com.ispc.destinosapp.service;

import com.ispc.destinosapp.dto.CityDTO;
import com.ispc.destinosapp.dto.DestinationRequestDTO;
import com.ispc.destinosapp.dto.DestinationResponseDTO;
import com.ispc.destinosapp.exception.NotFoundException;
import com.ispc.destinosapp.model.City;
import com.ispc.destinosapp.model.Destination;
import com.ispc.destinosapp.repository.ICityRepository;
import com.ispc.destinosapp.repository.IDestinationDao;
import com.ispc.destinosapp.wrapper.CityWrapper;
import org.springframework.stereotype.Service;

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
                city = cityRepository.findById(id).orElseThrow(() -> new NotFoundException("City not found"));
            }
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
        if (repository.findById(id).isPresent()) {

            repository.deleteById(id);
        } else {
            throw new NotFoundException("destination with id:" + id + " doesn't exist");
        }
    }


    @Override
    public Destination update(Long id, DestinationRequestDTO dto) {
        Destination dest = repository.findById(id).orElseThrow(() -> new NotFoundException("destination with id:" + id + " doesn't exist"));
        dest.setImage(dto.getImage());
        dest.setDescription(dto.getDescription());

        return repository.save(dest);

    }

    @Override
    public DestinationResponseDTO getById(Long id) {
        Destination dest = repository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("destination with id:" + id + " doesn't exist")
                );

        City city = null;
        if (dest.getLocation() != null) {
            city = cityRepository.findById(dest.getLocation().getId()).orElseThrow(() -> new NotFoundException("City not found"));
        }
        return DestinationResponseDTO.builder()
                .city(CityWrapper.summaryDto(city))
                .description(dest.getDescription())
                .image(dest.getImage())
                .name(dest.getName())
                .build();

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


    public Long create(DestinationRequestDTO dto) {
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
