package com.marks.hotelapi.service;

import com.marks.hotelapi.dto.QuartoDTO;
import com.marks.hotelapi.entity.Quarto;
import com.marks.hotelapi.exception.QuartoNaoEncontradoException;
import com.marks.hotelapi.repository.QuartoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuartoService {
    private final QuartoRepository quartoRepository;

    private QuartoDTO converterParaDto(Quarto quarto){
        return new QuartoDTO(
                quarto.getId(),
                quarto.getNomeQuarto()
        );
    }

    public QuartoDTO cadastrar(QuartoDTO dto){
        if (quartoRepository.existsByNomeQuarto(dto.getQuarto())){
            throw new RuntimeException("Quarto ja existe!");
        }

        Quarto novoQuarto = new Quarto();
        novoQuarto.setNomeQuarto(dto.getQuarto());

        return converterParaDto(quartoRepository.save(novoQuarto));

    }

    public List<QuartoDTO> listarTodos(){
        return quartoRepository.findAll().stream().map(this::converterParaDto).toList();
    }

    public QuartoDTO buscarPorId(Long id){
        return converterParaDto(quartoRepository.findById(id)
                .orElseThrow(() -> new QuartoNaoEncontradoException(id)));
    }

    public QuartoDTO atualizar(Long id, QuartoDTO dto){
        Quarto novoQuarto = quartoRepository.findById(id).orElseThrow(() -> new QuartoNaoEncontradoException(id));

        novoQuarto.setNomeQuarto(dto.getQuarto());

        return converterParaDto(quartoRepository.save(novoQuarto));
    }

    public void deleter(Long id){
        if (!quartoRepository.existsById(id)){
            throw new QuartoNaoEncontradoException(id);
        }

        quartoRepository.deleteById(id);
    }
}
