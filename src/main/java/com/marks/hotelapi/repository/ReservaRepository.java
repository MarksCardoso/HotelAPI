package com.marks.hotelapi.repository;

import com.marks.hotelapi.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    boolean existsByQuartoIdAndDataCheckInLessThanEqualAndDataCheckOutGreaterThanEqual(
            Long quartoId,
            LocalDate dataCheckOut,
            LocalDate dataCheckIn
    );
}
