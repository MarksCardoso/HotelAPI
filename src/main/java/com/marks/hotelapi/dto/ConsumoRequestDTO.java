package com.marks.hotelapi.dto;

import com.marks.hotelapi.entity.Quarto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsumoRequestDTO {
    private Long id;

    @NotBlank(message = "nome do produto/servico obrigatorio!")
    private String nome;

    @NotNull(message = "preco do produto/servico obrigatorio")
    private BigDecimal preco;

    @NotNull(message = "nome de quarto obrigatorio")
    private Long quartoId;
}
