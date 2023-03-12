package com.ispc.destinosapp.controller;


import com.ispc.destinosapp.dto.DestinationDto;
import com.ispc.destinosapp.service.DestinationServiceImpl;
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

    @GetMapping
    public ResponseEntity<List<DestinationDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }


    @PostMapping("/add")
    public ResponseEntity<Long> create(@RequestBody DestinationDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }


}
