package com.marks.hotelapi.service;

import com.marks.hotelapi.dto.ReservaRequestDTO;
import com.marks.hotelapi.dto.ReservaResponseDTO;
import com.marks.hotelapi.entity.Hospede;
import com.marks.hotelapi.entity.Quarto;
import com.marks.hotelapi.entity.Reserva;
import com.marks.hotelapi.enums.StatusReserva;
import com.marks.hotelapi.repository.HospedeRepository;
import com.marks.hotelapi.repository.QuartoRepository;
import com.marks.hotelapi.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaService {
    private final ReservaRepository reservaRepository;
    private final HospedeRepository hospedeRepository;
    private final QuartoRepository quartoRepository;

    private ReservaResponseDTO converterParaDto(Reserva reserva){
        return new ReservaResponseDTO(
                reserva.getId(),
                reserva.getStatus(),
                reserva.getDataCheckIn(),
                reserva.getDataCheckOut(),
                reserva.getDataCriacao(),
                reserva.getHospede().getNome(),
                reserva.getHospede().getEmail(),
                reserva.getQuarto().getNomeQuarto()
        );
    }

    public ReservaResponseDTO cadastrar(ReservaRequestDTO dto) {
        boolean quartoOcupado = reservaRepository
                .existsByQuartoIdAndDataCheckInLessThanEqualAndDataCheckOutGreaterThanEqual(
                        dto.getQuartoId(),
                        dto.getDataCheckOut(),
                        dto.getDataCheckIn()
                );

        if (quartoOcupado) {
            throw new RuntimeException("Quarto ja reservado nesse periodo!");
        }

        Hospede hospede = hospedeRepository.findById(dto.getHospedeId())
                .orElseThrow(() -> new RuntimeException("Hospede não encontrado!"));

        Quarto quarto = quartoRepository.findById(dto.getQuartoId())
                .orElseThrow(() -> new RuntimeException("Quarto não encontrado!"));

        Reserva novaReserva = new Reserva();
        novaReserva.setDataCheckIn(dto.getDataCheckIn());
        novaReserva.setDataCheckOut(dto.getDataCheckOut());
        novaReserva.setDataCriacao(LocalDateTime.now());
        novaReserva.setHospede(hospede);
        novaReserva.setQuarto(quarto);
        novaReserva.setStatus(StatusReserva.CONFIRMADA);

        return converterParaDto(reservaRepository.save(novaReserva));
    }

    public List<ReservaResponseDTO> listarTodos(){
        return reservaRepository.findAll().stream().map(this::converterParaDto).toList();
    }

    public ReservaResponseDTO listarPorId(Long id){
        return converterParaDto(reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva nao encontrada")));
    }

    public ReservaResponseDTO atualizar(Long id, ReservaRequestDTO dto){
        Reserva novaReserva = reservaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Reserva nao encontrada"));

        Quarto quarto = quartoRepository.findById(dto.getQuartoId()).orElseThrow(() -> new RuntimeException("Quarto nao encontrado"));

        Hospede hospede = hospedeRepository.findById(dto.getHospedeId()).orElseThrow(() -> new RuntimeException("Hospede nao encontrado"));

        novaReserva.setQuarto(quarto);
        novaReserva.setDataCheckIn(dto.getDataCheckIn());
        novaReserva.setDataCheckOut(dto.getDataCheckOut());
        novaReserva.setHospede(hospede);

        return converterParaDto(reservaRepository.save(novaReserva));
    }

    public void deletar(Long id){
        if (!reservaRepository.existsById(id)){
            throw new RuntimeException("Reserva nao existe");
        }

        reservaRepository.deleteById(id);
    }
}
