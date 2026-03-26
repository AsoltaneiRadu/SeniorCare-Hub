package org.example.healthapp.controllers;

import lombok.RequiredArgsConstructor;
import org.example.healthapp.models.Medic;
import org.example.healthapp.services.MedicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/medici")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class MedicController {

    private final MedicService medicService;

    @PostMapping
    public ResponseEntity<Medic> adaugaMedic(@RequestBody Medic medic) {
        Medic medicSalvat = medicService.adaugaMedic(medic);
        return new ResponseEntity<>(medicSalvat, HttpStatus.CREATED);
    }
}

