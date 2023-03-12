package com.ispc.destinosapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DestinationDto {
    private String name;
    private String description;
    private String image;
    private Long cityId;
}
