package com.ispc.destinosapp.wrapper;

import com.ispc.destinosapp.dto.DestinationRequestDTO;
import com.ispc.destinosapp.model.Destination;

public class DestinationWrapper {

    public static Destination toEntity(DestinationRequestDTO dto) {
        Destination entity = new Destination();
        if (dto == null) return entity;
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setImage(dto.getImage());
        return entity;
    }

    public static DestinationRequestDTO toDto(Destination entity) {
        DestinationRequestDTO dto = new DestinationRequestDTO();
        if (entity == null) return dto;
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setImage(entity.getImage());
        return dto;
    }


}
