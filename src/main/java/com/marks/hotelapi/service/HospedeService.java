package com.marks.hotelapi.service;

import com.marks.hotelapi.dto.HospedeDTO;
import com.marks.hotelapi.entity.Hospede;
import com.marks.hotelapi.repository.HospedeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HospedeService {
    private final HospedeRepository hospedeRepository;

    private HospedeDTO converterParaDTO(Hospede hospede){
        return new HospedeDTO(
          hospede.getId(),
          hospede.getNome(),
          hospede.getCpf(),
          hospede.getTelefone(),
          hospede.getEmail()
        );
    }

    public HospedeDTO cadastrar(HospedeDTO dto){
        if (hospedeRepository.existsByCpf(dto.getCpf())){
            throw new RuntimeException("Hospede com cpf " + dto.getCpf() + " ja cadastrado!");
        }

        Hospede novoHospede = new Hospede();
        novoHospede.setCpf(dto.getCpf());
        novoHospede.setNome(dto.getNome());
        novoHospede.setEmail(dto.getEmail());
        novoHospede.setTelefone(dto.getTelefone());

        return converterParaDTO(hospedeRepository.save(novoHospede));
    }

    public List<HospedeDTO> listarTodos (){
        return hospedeRepository.findAll().stream().map(this::converterParaDTO).toList();
    }

    public HospedeDTO buscarPorId(Long id){
        return converterParaDTO(hospedeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID nao encontrado")));
    }

    public HospedeDTO atualizar(Long id, HospedeDTO dto){
        Hospede novoHospede = hospedeRepository.findById(id).orElseThrow(() -> new RuntimeException("ID nao encontrado"));

        novoHospede.setTelefone(dto.getTelefone());
        novoHospede.setEmail(dto.getEmail());
        novoHospede.setCpf(dto.getCpf());
        novoHospede.setNome(dto.getNome());

        return converterParaDTO(hospedeRepository.save(novoHospede));
    }

    public void deleter(Long id){
        Hospede hospede = hospedeRepository.findById(id).orElseThrow(() ->new RuntimeException("hospede nao encontrado"));

        if (!hospede.getReservas().isEmpty()){
            throw new RuntimeException("Hospede possui reservas ativas!");
        }

        hospedeRepository.deleteById(id);
    }
}
