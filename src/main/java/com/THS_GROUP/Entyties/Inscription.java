package com.THS_GROUP.Entyties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inscriptions_thsgroup")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Inscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    @Column(name = "date_confirmation")
    private LocalDateTime dateConfirmation;

    @Enumerated(EnumType.STRING)
    private StatusInscription status = StatusInscription.EN_ATTENTE;

    // Relation avec Etudiant
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etudiant_id", nullable = false)
    @JsonBackReference("etudiant-inscriptions")
    private Etudiant etudiant;

    // Relation avec Formation
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "formation_id", nullable = false)
    @JsonIgnoreProperties({"inscriptions", "etudiants", "coursList"})
    private Formation formation;

    @PrePersist
    public void prePersist() {
        if (this.dateCreation == null) {
            this.dateCreation = LocalDateTime.now();
        }
    }

    // Méthode utilitaire pour confirmer l'inscription
    public void confirmer() {
        this.status = StatusInscription.COMFIMER;
        this.dateConfirmation = LocalDateTime.now();
    }

    // Méthode utilitaire pour refuser l'inscription
    public void refuser() {
        this.status = StatusInscription.REFUSER;
        this.dateConfirmation = null;
    }
}
