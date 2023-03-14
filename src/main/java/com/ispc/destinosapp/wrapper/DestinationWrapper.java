package com.ispc.destinosapp.wrapper;

import com.ispc.destinosapp.dto.DestinationDto;
import com.ispc.destinosapp.model.Destination;

public class DestinationWrapper {

    public static Destination toEntity(DestinationDto dto){
        Destination entity = new Destination();
        if (dto == null) return entity;
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setImage(dto.getImage());
        return entity;
    }

    public static DestinationDto toDto(Destination entity){
        DestinationDto dto = new DestinationDto();
        if (entity == null) return dto;
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setImage(entity.getImage());
        return dto;
    }
}
