package com.marks.hotelapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuartoDTO {
    private Long id;

    @NotBlank(message = "Digite o codigo do quarto")
    private String quarto;
}
