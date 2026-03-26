package org.example.healthapp.controllers;

import lombok.RequiredArgsConstructor;
import org.example.healthapp.dto.AuthResponse;
import org.example.healthapp.dto.LoginRequest;
import org.example.healthapp.models.Medic;
import org.example.healthapp.repositories.MedicRepository;
import org.example.healthapp.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {

    private final MedicRepository medicRepository;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest cerereLogin) {
        Optional<Medic> medicOptional = medicRepository.findByEmail(cerereLogin.getEmail());

        if (medicOptional.isEmpty() || !medicOptional.get().getParola().equals(cerereLogin.getParola())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email sau parola incorecte");
        }

        Medic medic = medicOptional.get();
        String token = jwtUtil.generateToken(medic.getEmail());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}

