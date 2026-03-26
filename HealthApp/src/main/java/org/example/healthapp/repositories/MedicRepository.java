package org.example.healthapp.repositories;

import org.example.healthapp.models.Medic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicRepository extends JpaRepository<Medic, Long> {
	Optional<Medic> findByEmail(String email);
}

