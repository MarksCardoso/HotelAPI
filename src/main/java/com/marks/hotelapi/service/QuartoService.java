package com.marks.hotelapi.service;

import com.marks.hotelapi.dto.QuartoDTO;
import com.marks.hotelapi.entity.Quarto;
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
                quarto.getQuarto()
        );
    }

    public QuartoDTO cadastrar(QuartoDTO dto){
        if (quartoRepository.existsByQuarto(dto.getQuarto())){
            throw new RuntimeException("Quarto ja existe!");
        }

        Quarto novoQuarto = new Quarto();
        novoQuarto.setQuarto(dto.getQuarto());

        return converterParaDto(quartoRepository.save(novoQuarto));

    }

    public List<QuartoDTO> listarTodos(){
        return quartoRepository.findAll().stream().map(this::converterParaDto).toList();
    }

    public QuartoDTO buscarPorId(Long id){
        return converterParaDto(quartoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quarto nao existe!")));
    }

    public QuartoDTO atualizar(Long id, QuartoDTO dto){
        Quarto novoQuarto = quartoRepository.findById(id).orElseThrow(() -> new RuntimeException("ID nao encontrado"));

        novoQuarto.setQuarto(dto.getQuarto());

        return converterParaDto(quartoRepository.save(novoQuarto));
    }

    public void deleter(Long id){
        if (!quartoRepository.existsById(id)){
            throw new RuntimeException("Quarto nao existe");
        }

        quartoRepository.deleteById(id);
    }
}
