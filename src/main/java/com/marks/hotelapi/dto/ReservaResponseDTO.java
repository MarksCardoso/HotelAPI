package com.marks.hotelapi.dto;

import com.marks.hotelapi.enums.StatusReserva;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservaResponseDTO {

    private Long id;
    private StatusReserva status;
    private LocalDate dataCheckIn;
    private LocalDate dataCheckOut;
    private LocalDateTime dataCriacao;

    private String nomeHospede;
    private String emailHospede;

    private String quarto;
}
