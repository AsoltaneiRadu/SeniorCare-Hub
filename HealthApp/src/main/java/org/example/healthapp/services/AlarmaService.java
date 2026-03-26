package org.example.healthapp.services;

import lombok.RequiredArgsConstructor;
import org.example.healthapp.models.Alarma;
import org.example.healthapp.repositories.AlarmaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlarmaService {

    private final AlarmaRepository alarmaRepository;

    public List<Alarma> obtineAlarmeActive(Long pacientId) {
        return alarmaRepository.findByPacientIdAndRezolvataFalse(pacientId);
    }

    public Alarma rezolvaAlarma(Long alarmaId, String observatii) {
        Alarma alarma = alarmaRepository.findById(alarmaId)
                .orElseThrow(() -> new RuntimeException("Alarma cu id " + alarmaId + " nu a fost gasita"));

        alarma.setRezolvata(true);
        alarma.setObservatiiRezolvare(observatii);
        alarma.setDataRezolvare(LocalDateTime.now());

        return alarmaRepository.save(alarma);
    }
}

