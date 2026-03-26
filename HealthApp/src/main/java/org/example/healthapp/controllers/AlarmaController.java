package org.example.healthapp.controllers;

import lombok.RequiredArgsConstructor;
import org.example.healthapp.models.Alarma;
import org.example.healthapp.services.AlarmaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/alarme")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AlarmaController {

    private final AlarmaService alarmaService;

    @GetMapping("/active/{pacientId}")
    public ResponseEntity<List<Alarma>> obtineAlarmeActive(@PathVariable Long pacientId) {
        List<Alarma> alarme = alarmaService.obtineAlarmeActive(pacientId);
        return ResponseEntity.ok(alarme);
    }

    @PutMapping("/{id}/rezolvare")
    public ResponseEntity<Alarma> rezolvaAlarma(@PathVariable Long id, @RequestBody String observatii) {
        Alarma alarmaRezolvata = alarmaService.rezolvaAlarma(id, observatii);
        return ResponseEntity.ok(alarmaRezolvata);
    }
}

