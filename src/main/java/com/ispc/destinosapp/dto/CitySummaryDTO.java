package com.ispc.destinosapp.dto;

import com.ispc.destinosapp.model.Climate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CitySummaryDTO {
    private Long id;
    private String name;
    private String province;
    private String country;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private Climate climate;
}
