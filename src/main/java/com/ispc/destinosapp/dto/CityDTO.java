package com.ispc.destinosapp.dto;

import com.ispc.destinosapp.model.Climate;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityDTO {
    private String name;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String country;
    private String province;
    @Enumerated(EnumType.STRING)
    private Climate climate;
    private String language;
    private String currency;
    private List<DestinationResponseDTO> destinations = new ArrayList<>();

}
