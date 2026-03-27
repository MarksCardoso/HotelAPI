package com.marks.hotelapi.controller;

import com.marks.hotelapi.dto.HospedeDTO;
import com.marks.hotelapi.service.HospedeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hospede")
public class HospedeController {
    private final HospedeService hospedeService;

    @PostMapping
    public ResponseEntity<HospedeDTO> cadastrar(@Valid @RequestBody HospedeDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(hospedeService.cadastrar(dto));
    }

    @GetMapping
    public ResponseEntity<List<HospedeDTO>> listarTodos (){
        return ResponseEntity.ok(hospedeService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospedeDTO> listarPorId (@PathVariable Long id){
        return ResponseEntity.ok(hospedeService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HospedeDTO> atualizar (@PathVariable Long id, @Valid @RequestBody HospedeDTO dto){
        return ResponseEntity.ok(hospedeService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        hospedeService.deleter(id);
        return ResponseEntity.noContent().build();
    }
}
