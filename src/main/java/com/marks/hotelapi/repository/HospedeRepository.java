package com.marks.hotelapi.repository;

import com.marks.hotelapi.entity.Hospede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospedeRepository extends JpaRepository<Hospede, Long> {
    boolean existsByCpf(String cpf);
}
