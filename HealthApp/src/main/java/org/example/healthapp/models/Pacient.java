package org.example.healthapp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pacienti")
public class Pacient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nume")
    private String nume;

    @Column(name = "prenume")
    private String prenume;

    @Column(name = "varsta")
    private Integer varsta;

    @Column(name = "cnp")
    private String cnp;

    @Column(name = "adresa")
    private String adresa;

    @Column(name = "oras")
    private String oras;

    @Column(name = "judet")
    private String judet;

    @Column(name = "numar_telefon")
    private String numarTelefon;

    @Column(name = "email")
    private String email;

    @Column(name = "profesie")
    private String profesie;

    @Column(name = "loc_de_munca")
    private String locDeMunca;

    @Lob
    @Column(name = "istoric_medical", columnDefinition = "TEXT")
    private String istoricMedical;

    @Lob
    @Column(name = "lista_alergii", columnDefinition = "TEXT")
    private String listaAlergii;

    @Column(name = "medic_id")
    private Long medicId;
}

