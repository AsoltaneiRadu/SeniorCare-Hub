package org.example.healthapp.repositories;

import org.example.healthapp.models.Alarma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlarmaRepository extends JpaRepository<Alarma, Long> {

    List<Alarma> findByPacientIdAndRezolvataFalse(Long pacientId);
}

