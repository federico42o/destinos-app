package com.ispc.destinosapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String country;
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("location")
    private List<Destination> destinations;

    public City(String name, BigDecimal latitude, BigDecimal longitude, String country) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country != null ? country : "Argentina";
        this.destinations = new ArrayList<>();
    }

}
