package com.marks.hotelapi.controller;

import com.marks.hotelapi.dto.ConsumoRequestDTO;
import com.marks.hotelapi.dto.ConsumoResponseDTO;
import com.marks.hotelapi.service.ConsumoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/consumo")
public class ConsumoController {
    private final ConsumoService consumoService;

    @PostMapping
    public ResponseEntity<ConsumoResponseDTO> criar(@Valid @RequestBody ConsumoRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(consumoService.criar(dto));
    }
}
