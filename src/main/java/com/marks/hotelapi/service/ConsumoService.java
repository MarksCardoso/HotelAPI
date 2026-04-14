package com.marks.hotelapi.service;

import com.marks.hotelapi.dto.ConsumoRequestDTO;
import com.marks.hotelapi.dto.ConsumoResponseDTO;
import com.marks.hotelapi.entity.Consumo;
import com.marks.hotelapi.entity.Quarto;
import com.marks.hotelapi.exception.QuartoNaoEncontradoException;
import com.marks.hotelapi.repository.ConsumoRepository;
import com.marks.hotelapi.repository.QuartoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ConsumoService {
    private final ConsumoRepository consumoRepository;
    private final QuartoRepository quartoRepository;

    private ConsumoResponseDTO toDto(Consumo consumo) {
        return new ConsumoResponseDTO(
                consumo.getId(),
                consumo.getNome(),
                consumo.getValor()
        );
    }

    public ConsumoResponseDTO criar(ConsumoRequestDTO consumo){
        if (!quartoRepository.existsById(consumo.getQuartoId())){
            throw new QuartoNaoEncontradoException(consumo.getQuartoId());
        }

        Consumo novoConsumo = new Consumo();
        novoConsumo.setNome(consumo.getNome());
        novoConsumo.setValor(consumo.getPreco());
        novoConsumo.setDataCriacao(LocalDateTime.now());

        Quarto quarto = quartoRepository.findById(consumo.getQuartoId()).orElseThrow(() -> new QuartoNaoEncontradoException(consumo.getQuartoId()));

        novoConsumo.setQuarto(quarto);

        return toDto(consumoRepository.save(novoConsumo));
    }
}
