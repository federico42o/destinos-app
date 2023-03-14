package com.ispc.destinosapp.wrapper;

import com.ispc.destinosapp.dto.CityDTO;
import com.ispc.destinosapp.dto.CitySummaryDTO;
import com.ispc.destinosapp.model.City;

public class CityWrapper {
    public static City toEntity(CityDTO dto) {

        if (dto == null) return new City();

        return City.builder()
                .name(dto.getName())
                .country(dto.getCountry())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .language(dto.getLanguage())
                .climate(dto.getClimate())
                .currency(dto.getCurrency())
                .province(dto.getProvince())
                .build();
    }

    public static CityDTO toDto(City entity) {
        if (entity == null) return new CityDTO();

        return CityDTO.builder()
                .name(entity.getName())
                .country(entity.getCountry())
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .language(entity.getLanguage())
                .climate(entity.getClimate())
                .currency(entity.getCurrency())
                .province(entity.getProvince())
                .build();
    }

    public static CitySummaryDTO summaryDto(City entity) {
        if (entity == null) return new CitySummaryDTO();

        return CitySummaryDTO.builder()
                .name(entity.getName())
                .country(entity.getCountry())
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .climate(entity.getClimate())
                .province(entity.getProvince())
                .build();
    }

}
