package com.ispc.destinosapp.controller;


import com.ispc.destinosapp.dto.DestinationDTO;
import com.ispc.destinosapp.dto.DestinationResponseDTO;
import com.ispc.destinosapp.service.DestinationServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    public ResponseEntity<DestinationDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "Create destination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Destination created")})

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody DestinationDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body("Destination created with id: " + service.create(dto));
    }

    @Operation(summary = "Delete destination by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Destination with id " + id + " deleted successfully");
    }

    @Operation(summary = "get destination by city")
    @GetMapping("/search")
    public ResponseEntity<?> getByCity(@RequestParam String city) {
        return ResponseEntity.ok(service.getByCity(city));
    }
}
