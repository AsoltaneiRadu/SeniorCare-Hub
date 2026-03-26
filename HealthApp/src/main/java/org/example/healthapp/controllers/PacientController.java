package org.example.healthapp.controllers;

import lombok.RequiredArgsConstructor;
import org.example.healthapp.models.Pacient;
import org.example.healthapp.services.PacientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pacienti")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PacientController {

    private final PacientService pacientService;

    @PostMapping
    public ResponseEntity<Pacient> crearePacient(@RequestBody Pacient pacient) {
        Pacient pacientSalvat = pacientService.salvarePacient(pacient);
        return new ResponseEntity<>(pacientSalvat, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Pacient>> obtineTotiPacientii() {
        return ResponseEntity.ok(pacientService.obtineTotiPacientii());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pacient> obtinePacientDupaId(@PathVariable Long id) {
        return ResponseEntity.ok(pacientService.obtinePacientDupaId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pacient> actualizarePacient(@PathVariable Long id, @RequestBody Pacient pacient) {
        return ResponseEntity.ok(pacientService.actualizarePacient(id, pacient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> stergerePacient(@PathVariable Long id) {
        pacientService.stergerePacient(id);
        return ResponseEntity.noContent().build();
    }
}

