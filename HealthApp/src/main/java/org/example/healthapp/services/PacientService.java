package org.example.healthapp.services;

import lombok.RequiredArgsConstructor;
import org.example.healthapp.models.Pacient;
import org.example.healthapp.repositories.PacientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacientService {

    private final PacientRepository pacientRepository;

    public Pacient salvarePacient(Pacient pacient) {
        return pacientRepository.save(pacient);
    }

    public List<Pacient> obtineTotiPacientii() {
        return pacientRepository.findAll();
    }

    public Pacient obtinePacientDupaId(Long id) {
        return pacientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pacientul nu a fost gasit"));
    }

    public Pacient actualizarePacient(Long id, Pacient dateNoi) {
        Pacient pacientExistent = obtinePacientDupaId(id);

        pacientExistent.setNume(dateNoi.getNume());
        pacientExistent.setPrenume(dateNoi.getPrenume());
        pacientExistent.setVarsta(dateNoi.getVarsta());
        pacientExistent.setCnp(dateNoi.getCnp());
        pacientExistent.setAdresa(dateNoi.getAdresa());
        pacientExistent.setOras(dateNoi.getOras());
        pacientExistent.setJudet(dateNoi.getJudet());
        pacientExistent.setNumarTelefon(dateNoi.getNumarTelefon());
        pacientExistent.setEmail(dateNoi.getEmail());
        pacientExistent.setProfesie(dateNoi.getProfesie());
        pacientExistent.setLocDeMunca(dateNoi.getLocDeMunca());
        pacientExistent.setIstoricMedical(dateNoi.getIstoricMedical());
        pacientExistent.setListaAlergii(dateNoi.getListaAlergii());
        pacientExistent.setMedicId(dateNoi.getMedicId());

        return pacientRepository.save(pacientExistent);
    }

    public void stergerePacient(Long id) {
        if (!pacientRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pacientul nu a fost gasit");
        }
        pacientRepository.deleteById(id);
    }
}

