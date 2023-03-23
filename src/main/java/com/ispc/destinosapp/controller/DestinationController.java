package com.ispc.destinosapp.controller;


import com.ispc.destinosapp.dto.DestinationRequestDTO;
import com.ispc.destinosapp.dto.DestinationResponseDTO;
import com.ispc.destinosapp.model.Destination;
import com.ispc.destinosapp.service.DestinationServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/destinations")
@AllArgsConstructor
@Slf4j
public class DestinationController {

    private final DestinationServiceImpl service;

    @Operation(summary = "Get all destinations")

    @GetMapping
    public ResponseEntity<List<DestinationResponseDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @Operation(summary = "Get destination By Id")

    @GetMapping("/{id}")
    public ResponseEntity<DestinationResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "get destination by city")
    @GetMapping("/search")
    public ResponseEntity<?> getByCity(@RequestParam String city) {
        return ResponseEntity.ok(service.getByCity(city));
    }

    @Operation(summary = "Create destination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Destination created")})

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody @Valid DestinationRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @Operation(summary = "Delete destination by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Destination with id " + id + " deleted successfully");
    }


    @PutMapping("/{id}")
    public ResponseEntity<Destination> update(@RequestBody @Valid DestinationRequestDTO dto, @PathVariable Long id) {
        return ResponseEntity.ok(service.update(id, dto));
    }

}
