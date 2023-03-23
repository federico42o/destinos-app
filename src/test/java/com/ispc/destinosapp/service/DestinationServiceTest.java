package com.ispc.destinosapp.service;


import com.ispc.destinosapp.dto.DestinationRequestDTO;
import com.ispc.destinosapp.dto.DestinationResponseDTO;
import com.ispc.destinosapp.model.City;
import com.ispc.destinosapp.model.Destination;
import com.ispc.destinosapp.repository.ICityRepository;
import com.ispc.destinosapp.repository.IDestinationDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DestinationServiceTest {

    @Mock
    private IDestinationDao destinationRepository;

    @Mock
    private ICityRepository cityRepository;

    @InjectMocks
    private DestinationServiceImpl destinationService;

    @Test
    public void testCreateDestination() {
        City city = City.builder().id(1L).name("Buenos Aires").country("Argentina").longitude(new BigDecimal("-34.603722")).latitude(new BigDecimal("-58.381592")).build();

        Destination destination = Destination.builder().name("Cementerio de Recoleta")
                .id(1L)
                .image("image.jpg")
                .description("Un cementerio famoso")
                .location(city).build();

        when(cityRepository.findById(1L)).thenReturn(Optional.of(city));
        when(destinationRepository.save(any(Destination.class))).thenReturn(destination);

        // act
        Long destinationId = destinationService.create(DestinationRequestDTO.builder().name(destination.getName()).image(destination.getImage()).description(destination.getDescription()).cityId(city.getId()).build());

        // assert
        verify(destinationRepository, times(1)).save(any(Destination.class));
        assertNotNull(destinationId);
    }

    @Test
    public void testFindAllDestinationsMultipleResults() {
        City city = City.builder().id(1L).name("Buenos Aires").country("Argentina").longitude(new BigDecimal("-34.603722")).latitude(new BigDecimal("-58.381592")).build();
        Destination destination1 = Destination.builder().name("Cementerio de la Recoleta").id(1L).image("image1.jpg").description("Un cementerio famoso").location(city).build();
        Destination destination2 = Destination.builder().name("Catedral de Buenos Aires").id(2L).image("image2.jpg").description("Una catedral impresionante").location(city).build();
        List<Destination> destinationList = Arrays.asList(destination1, destination2);

        when(destinationRepository.findAll()).thenReturn(destinationList);

        List<DestinationResponseDTO> destinations = destinationService.findAll();

        verify(destinationRepository, times(1)).findAll();
        assertFalse(destinations.isEmpty());
        assertEquals(2, destinations.size());
        assertEquals(destination1.getName(), destinations.get(0).getName());
        assertEquals(destination2.getDescription(), destinations.get(1).getDescription());
    }

    @Test
    public void testFindAllDestinationsNoResults() {
        when(destinationRepository.findAll()).thenReturn(Collections.emptyList());

        List<DestinationResponseDTO> destinations = destinationService.findAll();

        verify(destinationRepository, times(1)).findAll();
        assertTrue(destinations.isEmpty());
    }

}
