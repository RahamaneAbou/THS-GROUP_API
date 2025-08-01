package com.THS_GROUP.Entyties;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "administrateurs_thsgroup")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Administrateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    // Pour éviter d'exposer le mot de passe dans le JSON
    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nomComplet;

    // Exemple : si tu veux relier cet admin à des opérations ou logs
    // private List<Operation> operations;

    // Constructeur sans ID pour la création
    public Administrateur(String email, String password, String nomComplet) {
        this.email = email;
        this.password = password;
        this.nomComplet = nomComplet;
    }
}