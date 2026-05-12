package com.marks.hotelapi.repository;

import com.marks.hotelapi.entity.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Long> {
    boolean existsByNomeQuarto(String quarto);
}
