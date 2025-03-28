package com.THS_GROUP.Entyties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cours")
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nom; // Exemple : "Algorithmes avancés"

    @Column(nullable = false)
    private String description; // Description du cours

   /* @ManyToOne
    @JoinColumn(name = "enseignant_id", nullable = false)
    @JsonIgnoreProperties("cours") 
    private Enseignant enseignant; // Enseignant responsable du cours */

    @ManyToOne
    @JoinColumn(name = "formation_id", nullable = false)
    @JsonIgnoreProperties("coursList") 
    private Formation formation; // Filière à laquelle le cours appartient

    @OneToMany(mappedBy = "cours", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("cours")
    private List<Note> notes; // Liste des notes attribuées pour ce cours

    // Champ pour stocker explicitement l'ID de l'enseignant
    @Column(name = "enseignant_id")
    private Long enseignantId;
    // Champ pour gérer la concurrence optimiste
    @Version
    private Integer version;
}