package org.example.healthapp.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.healthapp.models.Alarma;
import org.example.healthapp.models.Trasabilitate;
import org.example.healthapp.repositories.AlarmaRepository;
import org.example.healthapp.repositories.TrasabilitateRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrasabilitateService {

    private static final int TEMPERATURA_AM_LIMITA = 35;
    private static final int PULS_MIN = 50;
    private static final int PULS_MAX = 120;
    private static final int GLICEMIE_MIN = 60;

    private final TrasabilitateRepository trasabilitateRepository;
    private final AlarmaRepository alarmaRepository;

    public Trasabilitate salvareMasuratoare(Trasabilitate masuratoare) {
        verificareAlarme(masuratoare);
        return trasabilitateRepository.save(masuratoare);
    }

    private void verificareAlarme(Trasabilitate masuratoare) {
        if (masuratoare == null) {
            log.error("Masuratoarea primita este null si nu poate fi procesata.");
            throw new IllegalArgumentException("Masuratoarea nu poate fi null");
        }

        Long pacientId = masuratoare.getPacientId();

        if (Boolean.TRUE.equals(masuratoare.getGaz())) {
            log.error("[ALERTA] Scurgere de gaze detectata pentru pacientId={}", pacientId);
            salveazaAlarma(pacientId, "Scurgere de gaze detectata", "CRITICA");
        }

        Integer temperaturaAm = masuratoare.getTemperaturaAm();
        if (temperaturaAm != null && temperaturaAm > TEMPERATURA_AM_LIMITA) {
            log.warn("[ALERTA] Crestere temperatura ambientala: {}C pentru pacientId={}", temperaturaAm, pacientId);
            salveazaAlarma(pacientId, "Temperatura ambientala crescuta peste limita", "AVERTIZARE");
        }

        Integer puls = masuratoare.getPuls();
        if (puls != null && (puls < PULS_MIN || puls > PULS_MAX)) {
            log.warn("[ALERTA] Parametri cardiaci aberanti: puls={} bpm pentru pacientId={}", puls, pacientId);
            salveazaAlarma(pacientId, "Parametri cardiaci aberanti: puls in afara limitelor", "AVERTIZARE");
        }

        Integer glicemie = masuratoare.getGlicemie();
        if (glicemie != null && glicemie < GLICEMIE_MIN) {
            log.error("[ALERTA] Glicemie scazuta cu risc: {} mg/dL pentru pacientId={}", glicemie, pacientId);
            salveazaAlarma(pacientId, "Glicemie scazuta cu risc de coma", "CRITICA");
        }
    }

    private void salveazaAlarma(Long pacientId, String mesaj, String tip) {
        Alarma alarma = Alarma.builder()
                .pacientId(pacientId)
                .mesaj(mesaj)
                .tip(tip)
                .dataGenerare(LocalDateTime.now())
                .rezolvata(false)
                .build();

        alarmaRepository.save(alarma);
    }

    public List<Trasabilitate> obtineIstoricPacient(Long pacientId) {
        return trasabilitateRepository.findByPacientIdOrderByDataInregistrareAsc(pacientId);
    }
}

