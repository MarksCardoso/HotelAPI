package com.marks.hotelapi.controller;

import com.marks.hotelapi.dto.ReservaRequestDTO;
import com.marks.hotelapi.dto.ReservaResponseDTO;
import com.marks.hotelapi.service.ReservaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reserva")
public class ReservaController {
    private final ReservaService reservaService;

    @PostMapping
    public ResponseEntity<ReservaResponseDTO> cadastrar(@Valid @RequestBody ReservaRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaService.cadastrar(dto));
    }

    @GetMapping
    public ResponseEntity<List<ReservaResponseDTO>> listarTodos(){
        return ResponseEntity.ok().body(reservaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(reservaService.listarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ReservaRequestDTO dto){
        return ResponseEntity.ok().body(reservaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        reservaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
