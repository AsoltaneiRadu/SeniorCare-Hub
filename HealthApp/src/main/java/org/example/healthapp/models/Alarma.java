package org.example.healthapp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "alarme")
public class Alarma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "pacient_id")
    private Long pacientId;

    @Column(name = "mesaj")
    private String mesaj;

    @Column(name = "tip")
    private String tip;

    @Column(name = "data_generare")
    private LocalDateTime dataGenerare;

    @Builder.Default
    @Column(name = "rezolvata")
    private Boolean rezolvata = false;

    @Column(name = "observatii_rezolvare")
    private String observatiiRezolvare;

    @Column(name = "data_rezolvare")
    private LocalDateTime dataRezolvare;
}

