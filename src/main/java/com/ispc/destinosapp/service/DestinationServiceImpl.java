package com.ispc.destinosapp.service;

import com.ispc.destinosapp.dto.DestinationDto;
import com.ispc.destinosapp.model.City;
import com.ispc.destinosapp.model.Destination;
import com.ispc.destinosapp.repository.ICityRepository;
import com.ispc.destinosapp.repository.IDestinationDao;
import com.ispc.destinosapp.wrapper.DestinationWrapper;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DestinationServiceImpl implements IDestinationService {

    private final IDestinationDao repository;
    private final ICityRepository cityRepository;

    public DestinationServiceImpl(IDestinationDao repository, ICityRepository cityRepository) {
        this.repository = repository;
        this.cityRepository = cityRepository;
    }


    @Override
    public List<DestinationDto> findAll() {
        return repository.findAll().stream().map(
                destination -> DestinationDto.builder()
                        .name(destination.getName())
                        .description(destination.getDescription())
                        .image(destination.getImage())
                        .cityId(destination.getLocation() != null ? destination.getLocation().getId() : null)
                        .build()).collect(Collectors.toList());
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
    public DestinationDto update(DestinationDto dto) {
        return null;
    }

    @Override
        public DestinationDto getById(Long id) {
            Destination dest = repository.findById(id)
                    .orElseThrow(
                            () -> new NotFoundException("destination with id:" + id + " doesn't exist")
                    );
            DestinationDto dto = DestinationWrapper.toDto(dest);
            if(dest.getLocation() != null){
                    dto.setCityId(dest.getLocation().getId());
            }
            return dto;

    }


    public Long create(DestinationDto dto) {
        City city = cityRepository.findById(dto.getCityId()).orElseThrow(()->new NotFoundException("City not found"));
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
