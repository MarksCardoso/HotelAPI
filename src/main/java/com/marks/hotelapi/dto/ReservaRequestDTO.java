package com.marks.hotelapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservaRequestDTO {
    @NotNull
    private Long hospedeId;

    @NotNull
    private Long quartoId;

    @NotNull
    private LocalDate dataCheckIn;

    @NotNull
    private LocalDate dataCheckOut;
}
