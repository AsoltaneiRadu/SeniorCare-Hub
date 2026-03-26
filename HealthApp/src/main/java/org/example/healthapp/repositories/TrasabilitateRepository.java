package org.example.healthapp.repositories;

import org.example.healthapp.models.Trasabilitate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TrasabilitateRepository extends JpaRepository<Trasabilitate, Long> {

    // Returnează istoricul complet al înregistrărilor pentru un pacient după ID-ul său
    List<Trasabilitate> findByPacientId(Long pacientId);

    // Returnează istoricul ordonat crescător după data înregistrării (util pentru grafice)
    List<Trasabilitate> findByPacientIdOrderByDataInregistrareAsc(Long pacientId);

    // Returnează măsurătorile unui pacient între două date specificate
    List<Trasabilitate> findByPacientIdAndDataInregistrareBetween(
            Long pacientId,
            LocalDateTime startDate,
            LocalDateTime endDate
    );
}

