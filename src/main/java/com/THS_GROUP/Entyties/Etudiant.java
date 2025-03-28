package com.THS_GROUP.Entyties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "etudiants")
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, unique = true, length = 50)
    private String numMatricule;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false, unique = false)
    private String email;

    @Column(nullable = false)
    private String numeroTelephone;

    @ManyToOne
    @JoinColumn(name = "formation_id", nullable = false)
    @JsonIgnoreProperties("etudiants") // Ignorer la liste des étudiants dans Formation
    private Formation formation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusInscription statusInscription;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateInscription;

    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("etudiant") // Ignorer l'étudiant dans Note
    private List<Note> notes;

    @Version // Gère la concurrence optimiste
    private Integer version;
}