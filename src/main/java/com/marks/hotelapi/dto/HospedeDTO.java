package com.marks.hotelapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HospedeDTO {
    private Long id;

    @NotBlank(message = "Nome obrigatorio!")
    private String nome;

    @NotBlank(message = "CPF obrigatorio!")
    private String cpf;

    @NotBlank(message = "Telefone obrigatorio!")
    private String telefone;

    @Email(message = "Digite um email valido!")
    private String email;
}
