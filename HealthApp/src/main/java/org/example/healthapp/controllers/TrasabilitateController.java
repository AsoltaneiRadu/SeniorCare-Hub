package org.example.healthapp.controllers;

import lombok.RequiredArgsConstructor;
import org.example.healthapp.models.Trasabilitate;
import org.example.healthapp.services.TrasabilitateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trasabilitate")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TrasabilitateController {

    private final TrasabilitateService trasabilitateService;

    @PostMapping
    public ResponseEntity<Trasabilitate> salvareMasuratoare(@RequestBody Trasabilitate masuratoare) {
        Trasabilitate salvat = trasabilitateService.salvareMasuratoare(masuratoare);
        return new ResponseEntity<>(salvat, HttpStatus.CREATED);
    }

    @GetMapping("/pacient/{pacientId}")
    public ResponseEntity<List<Trasabilitate>> obtineIstoricPacient(@PathVariable Long pacientId) {
        List<Trasabilitate> istoric = trasabilitateService.obtineIstoricPacient(pacientId);
        return ResponseEntity.ok(istoric);
    }

    @GetMapping("/istoric/{pacientId}")
    public ResponseEntity<List<Trasabilitate>> obtineIstoricPentruGrafic(@PathVariable Long pacientId) {
        List<Trasabilitate> istoric = trasabilitateService.obtineIstoricPacient(pacientId);
        return ResponseEntity.ok(istoric);
    }
}

