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
@Table(name = "notes_thsgroup")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double valeur;

    @Column(name = "date_attribution")
    private LocalDateTime DateAttribution;

    // Relation avec Etudiant
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etudiant_id", nullable = false)
    @JsonBackReference("etudiant-notes")
    private Etudiant etudiant;

    // Relation avec Cours
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cours_id", nullable = false)
    @JsonIgnoreProperties({"notes", "formation"})
    private Cours cours;

    @PrePersist
    public void prePersist() {
        if (this.DateAttribution == null) {
            this.DateAttribution = LocalDateTime.now();
        }
    }
}
