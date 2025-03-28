package com.THS_GROUP.Entyties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "etudiant_id", nullable = false)
    @JsonIgnoreProperties({"notes", "formation"}) 
    private Etudiant etudiant; // Étudiant associé à la note

    @ManyToOne
    @JoinColumn(name = "cours_id", nullable = false)
    @JsonIgnoreProperties({"notes", "enseignant"}) 
    private Cours cours; // Cours associé à la note

    @Column(nullable = false)
    private double valeur; // Valeur de la note (ex. 15.5)

    @Temporal(TemporalType.TIMESTAMP)
    private Date DateAttribution; // Date d'attribution de la note
}