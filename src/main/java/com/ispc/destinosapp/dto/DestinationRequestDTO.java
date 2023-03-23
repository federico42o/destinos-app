package com.ispc.destinosapp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DestinationRequestDTO {
    @NotBlank(message = "Destination Name must be provided")
    @Size(min = 3, max = 40, message = "Destination name length must be between 3 and 40 characters")
    private String name;
    @NotBlank(message = "Please add a description")
    private String description;
    private String image;
    @NotNull(message = "City ID must be provided")
    @Min(value = 1, message = "City ID = 0 doesn't exist")
    private Long cityId;
}
