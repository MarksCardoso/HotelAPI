package com.marks.hotelapi.repository;

import com.marks.hotelapi.entity.Consumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumoRepository extends JpaRepository<Consumo, Long> {
}
