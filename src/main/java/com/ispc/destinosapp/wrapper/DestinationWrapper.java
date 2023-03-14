package com.ispc.destinosapp.wrapper;

import com.ispc.destinosapp.dto.DestinationDTO;
import com.ispc.destinosapp.model.Destination;

public class DestinationWrapper {

    public static Destination toEntity(DestinationDTO dto) {
        Destination entity = new Destination();
        if (dto == null) return entity;
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setImage(dto.getImage());
        return entity;
    }

    public static DestinationDTO toDto(Destination entity) {
        DestinationDTO dto = new DestinationDTO();
        if (entity == null) return dto;
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setImage(entity.getImage());
        return dto;
    }


}
