package com.THS_GROUP.Entyties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "etudiants")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class    Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(unique = true, nullable = false)
    private String email;

    private Integer age;

    @Column(name = "numero_telephone")
    private String numeroTelephone;

    @Column(name = "num_matricule", unique = true)
    private String numMatricule;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_inscription")
    private StatusInscription statusInscription = StatusInscription.EN_ATTENTE;

    @Column(name = "date_inscription")
    private LocalDateTime dateInscription;

    // Relation avec Formation - Éviter la boucle infinie
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "formation_id")
    @JsonIgnoreProperties({"etudiants", "coursList", "inscriptions"})
    private Formation formation;

    // Relation avec Notes - Utiliser JsonManagedReference
    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("etudiant-notes")
    private List<Note> notes;

    // Relation avec Inscriptions
    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("etudiant-inscriptions")
    private List<Inscription> inscriptions;

    // Méthode utilitaire pour générer le matricule
    @PrePersist
    public void generateMatricule() {
        if (this.numMatricule == null) {
            this.numMatricule = "ETU" + System.currentTimeMillis();
        }
        if (this.dateInscription == null) {
            this.dateInscription = LocalDateTime.now();
        }
    }
}

/*enum StatusInscription {
    EN_ATTENTE, COMFIMER, REFUSER
}
*/