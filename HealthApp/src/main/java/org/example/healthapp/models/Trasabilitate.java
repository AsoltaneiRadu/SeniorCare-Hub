package org.example.healthapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trasabilitate")
public class Trasabilitate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "pacient_id", nullable = false)
    private Long pacientId;

    @Column(name = "data_inregistrare", nullable = false)
    private LocalDateTime dataInregistrare;

    @Column(name = "ta")
    private String ta;

    @Column(name = "puls")
    private Integer puls;

    @Column(name = "temp_c")
    private Double tempC;

    @Column(name = "greutate")
    private Double greutate;

    @Column(name = "glicemie")
    private Integer glicemie;

    @Column(name = "lumina")
    private Boolean lumina;

    @Column(name = "gaz")
    private Boolean gaz;

    @Column(name = "umiditate")
    private Integer umiditate;

    @Column(name = "proximitate")
    private Boolean proximitate;

    @Column(name = "temperatura_am")
    private Integer temperaturaAm;
}
