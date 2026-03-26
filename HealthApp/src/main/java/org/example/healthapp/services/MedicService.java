package org.example.healthapp.services;

import lombok.RequiredArgsConstructor;
import org.example.healthapp.models.Medic;
import org.example.healthapp.repositories.MedicRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicService {

    private final MedicRepository medicRepository;

    public Medic adaugaMedic(Medic medic) {
        return medicRepository.save(medic);
    }
}

