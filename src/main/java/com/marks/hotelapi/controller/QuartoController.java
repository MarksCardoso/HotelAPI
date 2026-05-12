package com.marks.hotelapi.controller;

import com.marks.hotelapi.dto.QuartoDTO;
import com.marks.hotelapi.service.QuartoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quarto")
public class QuartoController {
    private final QuartoService quartoService;

    @PostMapping
    public ResponseEntity<QuartoDTO> cadastrar(@Valid @RequestBody QuartoDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(quartoService.cadastrar(dto));
    }

    @GetMapping
    public ResponseEntity<List<QuartoDTO>> listarTodos (){
        return ResponseEntity.ok(quartoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuartoDTO> listarPorId (@PathVariable Long id){
        return ResponseEntity.ok(quartoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuartoDTO> atualizar (@PathVariable Long id, @Valid @RequestBody QuartoDTO dto){
        return ResponseEntity.ok(quartoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        quartoService.deleter(id);
        return ResponseEntity.noContent().build();
    }
}
